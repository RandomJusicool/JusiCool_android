package com.example.design_system.component.button

sealed class ButtonState {
    object Enable: ButtonState()

    object Disable: ButtonState()
}