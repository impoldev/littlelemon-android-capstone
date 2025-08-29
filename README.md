# Little Lemon – Android Developer Capstone Project

This is the **Capstone Project** for the **Meta Android Developer Specialization**. It is a fully functional Android app built with **Jetpack Compose**, demonstrating native Android development.

## Overview

Little Lemon is a sample restaurant app that allows users to explore menu items and manage their profiles. The app demonstrates:

- Jetpack Compose UI components.
- Fetching and storing data from an external API.
- Navigation between multiple screens.
- Local data persistence using Room.

## Screens

1. **Onboarding Screen**  
   - First screen for new users.
   - Introduces the app and its features.

2. **Main Screen**
   - Accessible after login.
   - Includes a **hero section** and a **list of menu items**.
   - Menu items are fetched from a remote endpoint and stored in a local database.

3. **Profile Screen**
   - Displays logged-in user information.
   - Includes a **logout button**.

## Features

- Smooth **navigation** between screens using Jetpack Navigation Compose.
- **Data fetching** from a REST API using Ktor client.
- **Local caching** of menu items using Room database.
- **Image loading** with Glide.
- **Serialization** of JSON data using Kotlin Serialization.
- **Annotation Processing** with Kapt.
- Android libraries and Material Design components.
