package  com.example.githubusers.di


import com.example.githubusers.data.UsersRepositoryImpl
import com.example.githubusers.data.sources.remoteApi.*
import com.example.githubusers.domain.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {


    @Provides
    @Singleton
    fun provideNewsRepository(iOnlineDataSource: OnlineDataSource)
            = UsersRepositoryImpl(iOnlineDataSource) as UsersRepository



    @Provides
    @Singleton
    fun provideIOnlineDataSource( service: ApiHelper)
            = OnlineDataSourceImpl(service) as OnlineDataSource




    @Provides
    @Singleton
    fun provideIApiHelper( apiService: ApiService)
            = ApiHelperImpl(apiService) as ApiHelper



}


