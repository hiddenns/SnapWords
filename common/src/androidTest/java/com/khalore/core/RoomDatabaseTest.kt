package com.khalore.core

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.khalore.core.dao.AnalyticsDao
import com.khalore.core.dao.CardDao
import com.khalore.core.dao.WordCombinationDao
import com.khalore.core.database.AppDatabase
import com.khalore.core.mappers.toDomain
import com.khalore.core.mappers.toLocal
import com.khalore.core.model.analytics.DailyAnalytic
import com.khalore.core.model.card.Card
import com.khalore.core.model.word.WordsCombination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest {

    private lateinit var database: AppDatabase
    private lateinit var cardDao: CardDao
    private lateinit var wordCombinationDao: WordCombinationDao
    private lateinit var analyticsDao: AnalyticsDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        cardDao = database.cardDao()
        wordCombinationDao = database.wordCombinationDao()
        analyticsDao = database.analyticsDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertCard() = runBlocking {
        val card = Card(
            cardId = 1,
            wordCombination = WordsCombination(
                wordCombinationId = 1
            ),
        )

        val savedWordCombination = wordCombinationDao.insert(card.wordCombination.toLocal())
        val updatedCard = card.toLocal().copy(wordCombinationId = savedWordCombination)

        cardDao.insert(updatedCard)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            cardDao.getCardsFlow().map { list ->
                list.map { dto ->
                    dto.toDomain()
                }
            }.collect {
                assertTrue(it.contains(card))
                latch.countDown()

            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun insertDailyAnalytic() = runBlocking {
        val currentTime = System.currentTimeMillis()

        val analytics = DailyAnalytic(
            dayUtc = currentTime
        )

        analyticsDao.insert(analytics.toLocal())

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            analyticsDao.getAllDailyAnalytics().let { list ->
                assertTrue(list.contains(analytics.toLocal()))
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun swipeCardTest() = runBlocking {
        val card = Card(
            cardId = 22,
            wordCombination = WordsCombination(
                wordCombinationId = 22
            ),
        )

        val savedWordCombination = wordCombinationDao.insert(card.wordCombination.toLocal())
        val updatedCard = card.toLocal().copy(wordCombinationId = savedWordCombination)

        cardDao.insert(updatedCard.copy(correctResponses = 1))

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            cardDao.getCardsFlow().collect {
                it.map { dto ->
                    dto.toDomain()
                }.let { cards ->
                    val isContains = cards.firstOrNull()?.correctResponses == 1L
                    assertTrue(isContains)
                }
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }
}