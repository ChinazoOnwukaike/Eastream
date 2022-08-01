package com.eastream.eastream.screens.titles;

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
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.tooling.preview.Preview;
import androidx.navigation.NavController;
import com.eastream.eastream.model.BasicTitleInfo;
import com.eastream.eastream.model.ETitle;
import com.eastream.eastream.navigation.EastreamScreens;
import com.eastream.eastream.navigation.MenuItem;
import com.google.firebase.auth.FirebaseAuth;

@kotlin.OptIn(markerClass = {androidx.compose.foundation.ExperimentalFoundationApi.class})
@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000T\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a`\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0013H\u0007\u001a,\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0007\u001a\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0007\u001a2\u0010\u0018\u001a\u00020\u00062(\u0010\u0019\u001a$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u001c0\u001bj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u001c`\u001d0\u001aH\u0007\u001a\u001a\u0010\u001e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\u001f"}, d2 = {"networkString", "", "", "getNetworkString", "()Ljava/util/List;", "AppBar", "", "viewModel", "Lcom/eastream/eastream/screens/titles/TitlesViewModel;", "showSearch", "", "showNetworks", "showMenu", "navController", "Landroidx/navigation/NavController;", "networks", "networkName", "Landroidx/compose/runtime/MutableState;", "onNavigationIconClick", "Lkotlin/Function0;", "DropMenu", "ShowCard", "title", "Lcom/eastream/eastream/model/BasicTitleInfo;", "TitlesLazyColumn", "showList", "", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "TitlesScreen", "app_debug"})
public final class TitlesScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> networkString = null;
    
    @androidx.compose.runtime.Composable()
    public static final void TitlesScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.titles.TitlesViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AppBar(@org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.titles.TitlesViewModel viewModel, boolean showSearch, boolean showNetworks, boolean showMenu, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> networks, @org.jetbrains.annotations.NotNull()
    androidx.compose.runtime.MutableState<java.lang.String> networkName, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigationIconClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DropMenu(@org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.titles.TitlesViewModel viewModel, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> networks, @org.jetbrains.annotations.NotNull()
    androidx.compose.runtime.MutableState<java.lang.String> networkName) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<java.lang.String> getNetworkString() {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ShowCard(@org.jetbrains.annotations.NotNull()
    com.eastream.eastream.model.BasicTitleInfo title) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TitlesLazyColumn(@org.jetbrains.annotations.NotNull()
    java.util.List<java.util.HashMap<java.lang.String, java.lang.Object>> showList) {
    }
}