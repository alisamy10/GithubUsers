package  com.example.githubusers.di


import com.example.githubusers.common.NetworkAwareHandler
import com.example.githubusers.data.UsersRepositoryImpl
import com.example.githubusers.data.sources.homeCahedData.OfflineDataSource
import com.example.githubusers.data.sources.homeCahedData.OfflineDataSourceImpl
import com.example.githubusers.data.sources.homeCahedData.UsersDao
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
    fun provideUsersRepository(iOfflineDataSource: OfflineDataSource, iOnlineDataSource: OnlineDataSource,
                               iNetworkAwareHandler: NetworkAwareHandler)
            = UsersRepositoryImpl(iOnlineDataSource,iNetworkAwareHandler,iOfflineDataSource) as UsersRepository



    @Provides
    @Singleton
    fun provideIApiHelper( apiService: ApiService) = ApiHelperImpl(apiService) as ApiHelper

    @Provides
    @Singleton
    fun provideIOnlineDataSource( service: ApiHelper) = OnlineDataSourceImpl(service) as OnlineDataSource







    @Provides
    @Singleton
    fun provideIOfflineDataSource (usersDao: UsersDao) = OfflineDataSourceImpl(usersDao) as OfflineDataSource




}


