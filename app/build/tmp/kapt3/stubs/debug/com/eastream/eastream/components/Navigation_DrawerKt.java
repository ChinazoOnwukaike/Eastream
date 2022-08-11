package com.eastream.eastream.components;

import android.widget.Toast;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.outlined.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.tooling.preview.Preview;
import androidx.navigation.NavController;
import com.eastream.eastream.R;
import com.eastream.eastream.navigation.EastreamScreens;
import com.eastream.eastream.model.MenuItem;
import com.eastream.eastream.repository.StoreThemeMode;
import com.google.firebase.auth.FirebaseAuth;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a\b\u0010\u000b\u001a\u00020\u0001H\u0007\u001a\u0018\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\b\u0010\u0011\u001a\u00020\u0001H\u0007\u00a8\u0006\u0012"}, d2 = {"DrawerBody", "", "items", "", "Lcom/eastream/eastream/model/MenuItem;", "modifier", "Landroidx/compose/ui/Modifier;", "itemTextStyle", "Landroidx/compose/ui/text/TextStyle;", "onItemClick", "Lkotlin/Function1;", "DrawerHeader", "MenuDrawerBody", "navController", "Landroidx/navigation/NavController;", "scaffoldState", "Landroidx/compose/material/ScaffoldState;", "ModeSwitch", "app_debug"})
public final class Navigation_DrawerKt {
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.ui.tooling.preview.Preview()
    public static final void DrawerHeader() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DrawerBody(@org.jetbrains.annotations.NotNull()
    java.util.List<com.eastream.eastream.model.MenuItem> items, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.text.TextStyle itemTextStyle, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.eastream.eastream.model.MenuItem, kotlin.Unit> onItemClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MenuDrawerBody(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    androidx.compose.material.ScaffoldState scaffoldState) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ModeSwitch() {
    }
}