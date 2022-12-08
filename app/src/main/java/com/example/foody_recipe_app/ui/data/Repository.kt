package com.example.foody_recipe_app.ui.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

//doubt on what is ActivityRetainedScoped ?
@ActivityRetainedScoped
class Repository @Inject constructor(
     remoteDataSource: RemoteDataSource,
     localDataSource: LocalDataSource
) {

    val remote = remoteDataSource
    val local = localDataSource
}