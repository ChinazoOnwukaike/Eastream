package com.eastream.eastream.screens;

import android.util.Log;
import android.widget.Toast;
import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.lazy.*;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.outlined.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.tooling.preview.Preview;
import androidx.navigation.NavController;
import com.eastream.eastream.model.BasicTitleInfo;
import com.eastream.eastream.navigation.EastreamScreens;
import com.eastream.eastream.screens.search.SearchViewModel;
import com.eastream.eastream.screens.search.SearchWidgetState;
import com.eastream.eastream.screens.userprofile.UserProfileViewModel;
import com.google.firebase.auth.FirebaseAuth;
import java.util.*;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0003\u001aF\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00032\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u001a\b\u0010\u0013\u001a\u00020\u0001H\u0007\u001a&\u0010\u0014\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001a\n\u0010\u0017\u001a\u00020\u0003*\u00020\u0003\u00a8\u0006\u0018"}, d2 = {"NetworkCardsList", "", "networkName", "", "viewModel", "Lcom/eastream/eastream/screens/userprofile/UserProfileViewModel;", "listOfTitles", "Landroidx/compose/runtime/MutableState;", "", "Lcom/eastream/eastream/model/BasicTitleInfo;", "navController", "Landroidx/navigation/NavController;", "SearchAppBar", "text", "onTextChange", "Lkotlin/Function1;", "onCloseClicked", "Lkotlin/Function0;", "onSearchClicked", "SearchAppBarPreview", "UserProfileScreen", "searchViewModel", "Lcom/eastream/eastream/screens/search/SearchViewModel;", "titleWords", "app_debug"})
public final class UserProfileScreenKt {
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.foundation.ExperimentalFoundationApi()
    public static final void UserProfileScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.userprofile.UserProfileViewModel viewModel, @org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.search.SearchViewModel searchViewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void NetworkCardsList(java.lang.String networkName, com.eastream.eastream.screens.userprofile.UserProfileViewModel viewModel, androidx.compose.runtime.MutableState<java.util.List<com.eastream.eastream.model.BasicTitleInfo>> listOfTitles, androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SearchAppBar(@org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onTextChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCloseClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSearchClicked) {
    }
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.ui.tooling.preview.Preview()
    public static final void SearchAppBarPreview() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String titleWords(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$titleWords) {
        return null;
    }
}