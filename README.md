# ArchitectureDemo

A demo for an Android app architecture using MVVM, data binding, RxJava, dependency injection &amp; more.

An [APK build](ArchitectureDemo.apk) for convenience.

## Architecture

There are two screens - the list of posts and a post editor. The main building block is the MVVM pattern, so each screen has its own View Model and View classes (Activity, Fragment, Adapter & the layout XML). There is just one model - the Post. The Repository, which provides a list of Posts, can also be considered to be a part of the Model layer.

When you open the Activities and Fragments you'll notice they are almost empty, except for some boilerplate DI and initialization code. The actual business logic resides in the View Models.

### Object lifecycle

I've decided to use Dagger, because it allowed me to contain the instance creation in a single place, plus it's really convenient to manage the objects' life-cycle using custom scopes.

The basic premise is to divide the objects into 2 life-cycle scopes:

*App scope* - basically, these are the "singletons" that live throughout the lifetime of an app. Things like a REST API client, a database or logger might go here. These classes are instantiated in the AppModule or are annotated with @Singleton, if they are created using constructor injection. Therefore, there's no need to have global static variables in the Application class.

*Activity scope* - these only exist during the lifetime of an Activity (duh). The View Models are injected and alive only when their respective Activities are running, for example. Some are created in the ActivityModule or are annotated with @PerActivity. This means that every time I ask for an instance of an App scope class I get the same one, every time.

The way I limited the Activity scope to an Activity's lifetime was by adding a headless fragment to each Activity - the StateFragment. It's created when an Activity starts and it's responsible for keeping its state (even during configuration changes) until the Activity is finally destroyed. When the StateFragment gets destroyed, all of the Activity scope instances are wiped, as well.

### Layer separation

Activities need to interact with each other using a Context. However, I wanted to keep the View Models independent of the Android Framework. So I've abstracted away the Activity interactions to a INavigator interface, which is injected into View Models. The actual implementation of the Navigator, which does the dirty work, is injected with the Context so View Models can stay clean.

It's similar for ILocalization, which takes care of loading localized strings from the resources.

## Data binding use cases

Most of the magic is inside the BindingHelpers class, where all the custom BindingAdapters reside.

### findViewById()

Notice the lack thereof in any of the Activities and Fragments.

### View switching

The ProgressBar indicator, for example, is shown/hidden by changing a single variable.

### Image loading

To show a post's featured image, all I need is an image URL in the PostViewModel and bind it in the post list item layout.

### Action listeners

To manage the post-loading-button functionality + its hiding, all I need is a single Command instance. It's the same for other buttons or an View, because all Views can have the onClick() listener. That way I could attach a Command to the post's title, which opens its URL in a browser.

### User input

Editing a post's title and summary came down to writing and reading from 2 variables, which are bound to the EditTexts. No TextWatchers needed.

### Lists

The post-list RecyclerView's content is managed entirely by the collection in the PostListViewModel. When the collection changes, the UI is automatically updated.

### Custom fonts

When I'm searching for drawables, I stop searching for drawables and I download Font Awesome instead. True story.

The Likes & Comments icons are just a custom font in a TextView ;)

### Dynamic localization

Button labels don't have string resources explicitly assigned in the layouts. Instead I bound them to an instance of the ILocalization in a View Model. When a language change is toggled, a Locale is updated in the Resources and a language-changed event is triggered to notify the bindings to update.

## Unit tests

I added a simple unit test for the PostListViewModel. I can test the business logic of the post-list screen without depending on any Android classes or app state.
