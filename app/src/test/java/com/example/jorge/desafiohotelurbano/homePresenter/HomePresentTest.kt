package com.example.jorge.desafiohotelurbano.homePresenter


import com.example.jorge.desafiohotelurbano.api.ApiServiceInterface
import com.example.jorge.desafiohotelurbano.models.Results
import com.example.jorge.desafiohotelurbano.ui.list.ListContract
import com.example.jorge.desafiohotelurbano.ui.list.ListPresenter
import com.example.jorge.desafiohotelurbano.util.Constants
import com.example.jorge.desafiohotelurbano.util.TestSchedulerProvider
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

class HomePresentTest {

    private val view: ListContract.View = mock()
    private val api: ApiServiceInterface = mock()
    private lateinit var presenter: ListPresenter
    private lateinit var testScheduler: TestScheduler


    @Before
    fun setup() {

        presenter = ListPresenter(ApiServiceInterface.create(Constants.BASE_URL))
        presenter.attach(view)
        presenter.subscribe()

    }

    @Test
    fun test_getRepos_should_callSuccess() {

        doReturn(Observable.just(Results.CREATOR))
            .`when`(api)
            .getResultsList()

        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        presenter.loadData(testSchedulerProvider)

        testScheduler.triggerActions()
    }

}

