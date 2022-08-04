package com.eastream.eastream.screens.titles;

import android.util.Log;
import android.widget.Toast;
import androidx.compose.foundation.ExperimentalFoundationApi;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.lazy.*;
import androidx.compose.material.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.outlined.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.tooling.preview.Preview;
import androidx.navigation.NavController;
import com.eastream.eastream.model.BasicTitleInfo;
import com.eastream.eastream.model.ETitle;
import com.eastream.eastream.navigation.EastreamScreens;
import com.eastream.eastream.navigation.MenuItem;
import com.eastream.eastream.screens.search.SearchViewModel;
import com.eastream.eastream.screens.search.SearchWidgetState;
import com.google.firebase.auth.FirebaseAuth;

@kotlin.OptIn(markerClass = {androidx.compose.foundation.ExperimentalFoundationApi.class})
@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000N\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0086\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\bH\u0007\u001a$\u0010\u0015\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000fH\u0007\u001a\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a*\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007\u001a$\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\""}, d2 = {"networkString", "", "", "getNetworkString", "()Ljava/util/List;", "AppBar", "", "showSearch", "", "showNetworks", "showMenu", "navController", "Landroidx/navigation/NavController;", "networks", "networkName", "Landroidx/compose/runtime/MutableState;", "onIconClick", "Lkotlin/Function0;", "onSearchTriggered", "isSearch", "details", "DropMenu", "MenuDrawerBody", "ShowCard", "title", "Lcom/eastream/eastream/model/BasicTitleInfo;", "addTitle", "modifier", "Landroidx/compose/ui/Modifier;", "TitlesScreen", "viewModel", "Lcom/eastream/eastream/screens/titles/TitlesViewModel;", "searchViewModel", "Lcom/eastream/eastream/screens/search/SearchViewModel;", "app_debug"})
public final class TitlesScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> networkString = null;
    
    @androidx.compose.runtime.Composable()
    public static final void TitlesScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.titles.TitlesViewModel viewModel, @org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.search.SearchViewModel searchViewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MenuDrawerBody(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AppBar(boolean showSearch, boolean showNetworks, boolean showMenu, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> networks, @org.jetbrains.annotations.Nullable()
    androidx.compose.runtime.MutableState<java.lang.String> networkName, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onIconClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSearchTriggered, boolean isSearch, boolean details) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DropMenu(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> networks, @org.jetbrains.annotations.NotNull()
    androidx.compose.runtime.MutableState<java.lang.String> networkName) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<java.lang.String> getNetworkString() {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ShowCard(@org.jetbrains.annotations.NotNull()
    com.eastream.eastream.model.BasicTitleInfo title, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, boolean addTitle, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}