package com.example.jorge.desafiohotelurbano.util

import io.reactivex.Scheduler

/**
Provider SchedulerProvider
 **/

interface SchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}