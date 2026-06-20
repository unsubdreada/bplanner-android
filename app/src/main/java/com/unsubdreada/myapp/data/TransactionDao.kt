package com.unsubdreada.myapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert // Добавление записи в бд
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("DELETE FROM transactions")
    suspend fun clearAllTransactions()

    @Query("SELECT * FROM transactions ORDER BY id DESC")
    fun getAllTransaction(): Flow<List<TransactionEntity>>

    @Query(
        """
        SELECT * FROM transactions
        WHERE (comment LIKE '%' || :searchQuery || '%') -- Поиск по примечанию
        AND (:categoryFilter = '' OR (
            CASE category 
                -- Расходы
                WHEN 'PRODUCTS' THEN 'Продукты'
                WHEN 'CAFE' THEN 'Кафе и рестораны'
                WHEN 'TRANSPORT' THEN 'Транспорт' 
                WHEN 'HOUSING' THEN 'ЖКХ и Дом' 
                WHEN 'HEALTH' THEN 'Здоровье' 
                WHEN 'CLOTHES' THEN 'Одежда' 
                WHEN 'ENTERTAINMENT' THEN 'Развлечения' 
                WHEN 'EDUCATION' THEN 'Образование' 
                WHEN 'GIFTS_EXP' THEN 'Подарки' 
                WHEN 'OTHER_EXP' THEN 'Другие расходы' 
                -- Доходы 
                WHEN 'SALARY' THEN 'Зарплата' 
                WHEN 'BONUS' THEN 'Премия' 
                WHEN 'INVESTMENTS' THEN 'Инвестиции' 
                WHEN 'FREELANCE' THEN 'Подработка' 
                WHEN 'GIFTS_INC' THEN 'Подарки' 
                WHEN 'RENT' THEN 'Сдача в аренду' 
                WHEN 'CASHBACK' THEN 'Кэшбэк' 
                WHEN 'OTHER_INC' THEN 'Другие доходы' 
                ELSE 'Разное' 
            END
        ) = :categoryFilter)
        AND (
            :filterType = 'ALL'
            OR (:filterType = 'INCOME' AND isIncome = 1)
            OR (:filterType = 'EXPENSE' AND isIncome = 0)
        )
        ORDER BY 
            CASE WHEN :sortType = 'CREATED_DESC' THEN createdAt END DESC, -- По дате создания - сначала новые
            CASE WHEN :sortType = 'CREATED_ASC' THEN createdAt END ASC, -- По дате создания - сначала старые
            CASE WHEN :sortType = 'TRANSACTION_DESC' THEN date END DESC, -- По дате транзакции - сначала новые
            CASE WHEN :sortType = 'TRANSACTION_ASC' THEN date END ASC -- По дате транзакции - сначала старые
    """
    ) // Получение всех записей из бд
    fun getSearchTransactions(
        searchQuery: String,
        categoryFilter: String,
        sortType: String,
        filterType: String
    ): Flow<List<TransactionEntity>>

    @Query("DELETE FROM transactions WHERE id IN (:ids)") // Удаление записи/записей из бд
    suspend fun delTransactionsById(ids: List<Int>)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity) // Изменение записи в бд
}