package com.chatia.login.data.repoImpl

import com.chatia.data.source.NetworkDataSource
import com.chatia.data.source.toDomain
import com.chatia.login.data.model.LoginRequestDto
import com.chatia.login.data.remote.LoginService
import com.chatia.login.domain.model.LoginResponse
import com.chatia.login.domain.repo.LoginRepository
class LoginRepositoryImpl constructor(
    private val networkDataSource: NetworkDataSource<LoginService>,
): LoginRepository {
    override suspend fun login(
        username: String,
        password: String
    ): Result<LoginResponse> = networkDataSource.performRequest(
    request = {
        login(
            requestBody = LoginRequestDto(
                userName = username,
                password = password,
            )
        )
    },
    onSuccess = { response, headers ->
         Result.success(response)
    },
    onError = { errorResponse, code ->
        Result.error(errorResponse.toDomain(code))
    },
    )
}


//package com.khales.login.data.repositoryImpl

//import android.util.Log
//import com.khales.data.BuildConfig
//import com.khales.data.di.PosNumberProvider
//import com.khales.data.error.toDomain
//import com.khales.data.source.NetworkDataSource
//import com.khales.domain.LoginModel
//import com.khales.domain.result.Result
//import com.khales.login.data.mapper.LoginMapper
//import com.khales.login.data.request.LoginRequestBody
//import com.khales.login.domain.repository.LoginRemoteRepo
//import com.khales.protodatastore.manager.LoginResponseDataStoreInterface
//import data.service.LoginService
//import kotlinx.coroutines.runBlocking
//import javax.inject.Inject
//
//class LoginRemoteRepoImpl @Inject constructor(
//    private val networkDataSource: NetworkDataSource<LoginService>,
//    private val loginMapper: LoginMapper,
//    private val loginDataStore: LoginResponseDataStoreInterface,
//    private var posNumberProvider: PosNumberProvider,
//) : LoginRemoteRepo {
//    override suspend fun login(
//        userName: String,
//        password: String,
//        posNumber: String,
//    ): Result<LoginModel> =
//        networkDataSource.performRequest(
//            request = {
//                posNumberProvider.set(posNumber)
//                login(
//                    requestBody = LoginRequestBody(
//                        userName = userName,
//                        password = password,
//                        posSn = posNumber,
//                    ),
//                    posNumber = posNumber, //
//                ).await()
//            },
//            onSuccess = { response, headers ->
//                val model = loginMapper.toDomain(response)
//                runBlocking {
//                    loginDataStore.setEmpCode(response.empCode!!)
//                    loginDataStore.setRoleCode(response.role!!.roleCode!!)
//
////        response.role?.let {
////          response.role.roleCode?.let {
////            loginDataStore.setRoleCode(response.role.roleCode)
////          }
////        }
//
//                    loginDataStore.setEmpName(response.name.orEmpty())
//                    loginDataStore.setSyncInterval(response.backgroundInterval)
//                    if (BuildConfig.FLAVOR == "aswan") {
//                        loginDataStore.setWalletId(response.wallet?.find { it?.walletType == 1 }?.walletId ?: "")
//                    }
//
//                    if (response.status == 200) {
//                        loginDataStore.setIsWalletClosed(false)
//                    }
//
//                    if (response.status == 206) {
//                        loginDataStore.setIsWalletClosed(true)
//                    }
//                }
//
//                Log.e("TAG132", "login: " + loginDataStore.getIsWalletClosedCode())
//                Result.success(model)
//            },
//            onError = { errorResponse, code ->
//                Result.error(errorResponse.toDomain(code))
//            },
//        )
//}
