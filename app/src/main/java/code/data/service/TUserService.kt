package code.data.service

import code.data.ResultData
import code.model.Profile
import retrofit2.http.Body
import retrofit2.http.POST

interface TUserService {

    /**
     * 插入用户资料
     * @param values 包含用户资料信息的Map
     * @return ResultData包装的响应数据
     */
    @POST("/TUserCtrl/profileInsert")
    suspend fun profileInsert(@Body values: Profile): ResultData<Any>

    /**
     * 更新用户资料
     * @param values 包含用户资料更新信息的Map
     * @return ResultData包装的响应数据
     */
    @POST("/TUserCtrl/profileUpdate")
    suspend fun profileUpdate(@Body values: Profile): ResultData<Any>

    @POST("/TUserCtrl/selectProfileByEmail")
    suspend fun selectProfileByEmail(@Body values: Map<String, String>): ResultData<Profile>
    /**
     * 根据用户ID查询用户资料
     * @param values 包含用户ID的Map
     * @return ResultData包装的响应数据
     */
    @POST("/TUserCtrl/selectProfileByUserId")
    suspend fun selectProfileByUserId(@Body values: Map<String, Int>): ResultData<Profile>
}