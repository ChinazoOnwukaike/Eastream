package com.eastream.eastream.components;

import android.widget.Toast;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.navigation.NavController;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001aU\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001ac\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a\b\u0010\u0018\u001a\u00020\u0001H\u0007\u001a]\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001d\u001a\u0016\u0010\u001e\u001a\u00020\u00012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u0006H\u0007\u001a\u0012\u0010\u001f\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006 "}, d2 = {"EastreamLogoText", "", "modifier", "Landroidx/compose/ui/Modifier;", "EmailInput", "emailState", "Landroidx/compose/runtime/MutableState;", "", "labelId", "enabled", "", "imeAction", "Landroidx/compose/ui/text/input/ImeAction;", "onAction", "Landroidx/compose/foundation/text/KeyboardActions;", "EmailInput-BhUwt6o", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/MutableState;Ljava/lang/String;ZILandroidx/compose/foundation/text/KeyboardActions;)V", "InputField", "valueState", "isSingleLine", "keyboardType", "Landroidx/compose/ui/text/input/KeyboardType;", "InputField-ADs98rI", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/MutableState;Ljava/lang/String;ZZIILandroidx/compose/foundation/text/KeyboardActions;)V", "ModeSwitch", "PasswordInput", "passwordState", "passwordVisibility", "PasswordInput-jF9rHs0", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/MutableState;Ljava/lang/String;ZLandroidx/compose/runtime/MutableState;ILandroidx/compose/foundation/text/KeyboardActions;)V", "PasswordVisibility", "SplashScreenText", "app_debug"})
public final class ComponentsKt {
    
    @androidx.compose.runtime.Composable()
    public static final void EastreamLogoText(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SplashScreenText(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PasswordVisibility(@org.jetbrains.annotations.NotNull()
    androidx.compose.runtime.MutableState<java.lang.Boolean> passwordVisibility) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ModeSwitch() {
    }
}