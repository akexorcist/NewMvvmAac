package com.akexorcist.extralivedatatest.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.akexorcist.extralivedatatest.domain.result.Result
import timber.log.Timber

abstract class UseCase<in P, R> {

    private val taskScheduler = DefaultScheduler

    /** Executes the use case asynchronously and places the [Result] in a MutableLiveData
     *
     * @param parameters the input parameters to run the use case with
     * @param result the MutableLiveData where the result is posted to
     *
     */
    operator fun invoke(parameters: P, result: MutableLiveData<Result<R>>) {
        // result.value = Result.Loading TODO: add data to Loading to avoid glitches
        try {
            DefaultScheduler.execute {
                try {
                    execute(parameters).let { useCaseResult ->
                        result.postValue(
                            Result.Success(
                                useCaseResult
                            )
                        )
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                    result.postValue(
                        Result.Error(
                            e
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Timber.d(e)
            result.postValue(Result.Error(e))
        }
    }

    /** Executes the use case asynchronously and returns a [Result] in a new LiveData object.
     *
     * @return an observable [LiveData] with a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    operator fun invoke(parameters: P): LiveData<Result<R>> {
        val liveCallback: MutableLiveData<Result<R>> = MutableLiveData()
        this(parameters, liveCallback)
        return liveCallback
    }

    /** Executes the use case synchronously  */
    fun executeNow(parameters: P): Result<R> {
        return try {
            Result.Success(execute(parameters))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P): R
}

operator fun <R> UseCase<Unit, R>.invoke(): LiveData<Result<R>> = this(Unit)
operator fun <R> UseCase<Unit, R>.invoke(result: MutableLiveData<Result<R>>) = this(Unit, result)