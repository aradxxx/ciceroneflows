package com.github.aradxxx.ciceroneflowssample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.github.aradxxx.ciceroneflowssample.tabfragment.Tab1
import com.github.aradxxx.ciceroneflowssample.tabfragment.Tab2
import com.github.aradxxx.ciceroneflowssample.tabfragment.Tab3
import com.github.aradxxx.ciceroneflowssample.tabfragment.TabContainerFragment

@Module
interface FragmentBindingModule {
    @ContributesAndroidInjector
    fun provideTab1Fragment(): Tab1

    @ContributesAndroidInjector
    fun provideTab2Fragment(): Tab2

    @ContributesAndroidInjector
    fun provideTab3Fragment(): Tab3

    @ContributesAndroidInjector
    fun provideTabContainerFragment(): TabContainerFragment
}
