package  com.example.githubusers.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.githubusers.R
import com.example.githubusers.common.NetworkAwareHandler
import com.example.githubusers.common.NetworkHandlerImpl
import com.example.githubusers.data.sources.homeCahedData.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        UserDataBase::class.java, "USERS_DATABASE_NAME"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideNewsDao(db: UserDataBase) = db.getUsersDao()


    @Provides
    @Singleton
    fun provideINetworkAwareHandler( @ApplicationContext context: Context)
            = NetworkHandlerImpl(context) as NetworkAwareHandler




}





