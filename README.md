# SoundPoolWrapper
Simple wrapper for Android's SoundPool class

## Description

Enables using SoundPool without taking care of soundId.

## Features

- No need to care of soundId. Just use resourceId to load/play sounds
- Can check whether sound specified by resourceId is loaded or not
- Convenience callback to know when the resource is finished loading

## Usage
Write the below in your build.gradle
```
repositories {
    maven { url 'http://raw.github.com/yuishihara/SoundPoolWrapper/master/repository/' }
}

dependencies {
    compile 'jp.techblog.epsiloncode:soundpoolwrapper:0.0.2'
}
```

## License

[Apache Software Licence 2.0](http://www.apache.org/licenses/LICENSE-2.0)
