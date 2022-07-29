package com.eastream.eastream.screens.login;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.*;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ExperimentalComposeUiApi;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.platform.LocalSoftwareKeyboardController;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.tooling.preview.Preview;
import androidx.navigation.NavController;
import com.eastream.eastream.R;
import com.eastream.eastream.navigation.EastreamScreens;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a.\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0007\u001a8\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\n2\u001a\b\u0002\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0007\u00a8\u0006\u0012"}, d2 = {"LoginScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/eastream/eastream/screens/login/LoginScreenViewModel;", "SubmitButton", "textId", "", "loading", "", "validInputs", "onClick", "Lkotlin/Function0;", "UserForm", "isCreateAccount", "onDone", "Lkotlin/Function2;", "app_debug"})
public final class LogInScreenKt {
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.ui.tooling.preview.Preview()
    @androidx.compose.ui.ExperimentalComposeUiApi()
    public static final void LoginScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.login.LoginScreenViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.ui.ExperimentalComposeUiApi()
    public static final void UserForm(boolean loading, boolean isCreateAccount, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> onDone) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SubmitButton(@org.jetbrains.annotations.NotNull()
    java.lang.String textId, boolean loading, boolean validInputs, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}