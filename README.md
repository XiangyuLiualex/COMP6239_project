# Basic Layouts in Compose Codelab

This folder contains the source code for
the [Basic Layouts in Compose Codelab](https://developer.android.com/codelabs/jetpack-compose-layouts)

## License

```
Copyright 2022 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Image asset attributions

[fc1_short_mantras.jpg](https://www.pexels.com/photo/body-of-water-view-1825206/) - Elizaveta Kozorezova
[fc2_nature_meditations.jpg](https://www.pexels.com/photo/photo-of-green-leaves-3571551/) - Nothing Ahead
[fc3_stress_and_anxiety.jpg](https://www.pexels.com/photo/aerial-view-of-body-of-water-1557238/) - Jim
[fc4_self_massage.jpg](https://www.pexels.com/photo/photography-of-stones-1029604/) - Scott Webb
[fc5_overwhelmed.jpg](https://www.pexels.com/photo/white-clouds-3560044/) - Ruvim
[fc6_nightly_wind_down.jpg](https://www.pexels.com/photo/time-lapse-photo-of-stars-on-night-924824/) - Jakub Novacek
[ab1_inversions.jpg](https://www.pexels.com/photo/low-angle-view-of-woman-relaxing-on-beach-against-blue-sky-317157/) - Chevanon Photography
[ab2_quick_yoga.jpg](https://www.pexels.com/photo/photo-of-woman-stretching-her-body-1812964/) - Agung Pandit Wiguna
[ab3_stretching.jpg](https://www.pexels.com/photo/photo-of-women-stretching-together-4056723/) - Cliff Booth
[ab4_tabata.jpg](https://www.pexels.com/photo/fashion-man-people-art-4662438/) - Elly Fairytale
[ab5_hiit.jpg](https://www.pexels.com/photo/man-wearing-white-pants-under-blue-sky-999309/) - The Lazy Artist Gallery
[ab6_pre_natal_yoga.jpg](https://www.pexels.com/photo/woman-doing-yoga-396133/) - freestocks.org


分层逻辑：

1. UI层（使用Jetpack Compose）
   职责：构建和管理用户界面，响应用户输入，展示数据。
   技术：Jetpack Compose，这是一种声明式的UI工具库，允许开发者用更直观的方式构建Android应用的UI。
   代码特点：主要包含@Composable函数，这些函数定义了应用的视图和布局。
2. ViewModel层（Kotlin/Java）
   职责：作为UI层和数据处理层之间的桥梁，管理UI相关的数据状态和业务逻辑。ViewModel持有界面数据，处理数据变化，将业务逻辑的结果反馈给UI层。
   技术：ViewModel组件（一部分Jetpack库），LiveData或Flow，这些组件和库帮助处理数据流和生命周期管理。
   代码特点：不包含任何UI代码，只包含数据和状态管理逻辑。
3. Domain层（Kotlin/Java）
   职责：包含应用的业务逻辑。这一层定义了应用的核心功能，独立于其他层。
   技术：纯Kotlin或Java代码，用于定义业务实体（Entities）、业务规则和用例（Use Cases）。
   代码特点：通常不依赖任何特定的框架，确保业务逻辑的独立性和可重用性。
4. Data层（Kotlin/Java）
   职责：负责数据的持久化和网络通信，实现Domain层定义的接口。
   技术：Room（用于数据库交互）、Retrofit（用于网络请求）、其他可能的数据持久化或网络通信库。
   代码特点：包含数据访问对象（DAO）、数据传输对象（DTOs）、仓库实现（Repositories）等。
