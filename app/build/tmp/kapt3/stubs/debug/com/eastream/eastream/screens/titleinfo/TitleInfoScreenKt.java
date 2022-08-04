package com.eastream.eastream.screens.titleinfo;

import android.content.Intent;
import android.widget.Toast;
import androidx.compose.foundation.*;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.lazy.GridCells;
import androidx.compose.material.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.tooling.preview.Preview;
import androidx.navigation.NavController;
import com.eastream.eastream.model.ETitle;
import com.google.firebase.auth.FirebaseAuth;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0007\u001a.\u0010\u0006\u001a\u00020\u00012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bH\u0007\u001a\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\tH\u0003\u001a(\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0007\u001ah\u0010\u0014\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00182\"\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001aj\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u001b2\"\u0010\u001c\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001aj\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u001bH\u0007\u00a8\u0006\u001d"}, d2 = {"NetworkTile", "", "network", "", "link", "networkImg", "PosterRating", "titleInfo", "Landroidx/compose/runtime/MutableState;", "Lcom/eastream/eastream/model/ETitle;", "viewModel", "Lcom/eastream/eastream/screens/titleinfo/TitleInfoViewModel;", "isLiked", "", "Summary", "title", "TitleInfoScreen", "navController", "Landroidx/navigation/NavController;", "titleId", "WhereToWatch", "modifier", "Landroidx/compose/ui/Modifier;", "networks", "", "networkImgs", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "networkLinks", "app_debug"})
public final class TitleInfoScreenKt {
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.foundation.ExperimentalFoundationApi()
    public static final void TitleInfoScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.titleinfo.TitleInfoViewModel viewModel, @org.jetbrains.annotations.Nullable()
    java.lang.String titleId) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void Summary(com.eastream.eastream.model.ETitle title) {
    }
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.foundation.ExperimentalFoundationApi()
    public static final void PosterRating(@org.jetbrains.annotations.NotNull()
    androidx.compose.runtime.MutableState<com.eastream.eastream.model.ETitle> titleInfo, @org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.titleinfo.TitleInfoViewModel viewModel, @org.jetbrains.annotations.NotNull()
    androidx.compose.runtime.MutableState<java.lang.Boolean> isLiked) {
    }
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.foundation.ExperimentalFoundationApi()
    public static final void WhereToWatch(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> networks, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> networkImgs, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> networkLinks) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void NetworkTile(@org.jetbrains.annotations.NotNull()
    java.lang.String network, @org.jetbrains.annotations.NotNull()
    java.lang.String link, @org.jetbrains.annotations.NotNull()
    java.lang.String networkImg) {
    }
}