package com.khalore.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.khalore.core.dao.AnalyticsDao
import com.khalore.core.dao.CardDao
import com.khalore.core.dao.DeckDao
import com.khalore.core.dao.WordCombinationDao
import com.khalore.core.entity.CardLocal
import com.khalore.core.entity.WordCombinationLocal
import com.khalore.core.entity.analytics.DailyAnalyticLocal
import com.khalore.core.entity.deck.DeckLocal
import com.khalore.core.entity.deck.DeckWithCardLocal

@Database(
    entities = [CardLocal::class, WordCombinationLocal::class, DeckLocal::class,
        DeckWithCardLocal::class, DailyAnalyticLocal::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun wordCombinationDao(): WordCombinationDao
    abstract fun deckDao(): DeckDao
    abstract fun analyticsDao(): AnalyticsDao
}
