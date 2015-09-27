# SoundPoolWrapper
Simple wrapper for Android's SoundPool class

## Description

Enables using SoundPool without taking care of soundId.

## Features

- Use resourceId to play sounds
- Convenience callback to know when the resource is finished loading

## Usage
Write the below in your build.gradle
```
repositories {
    maven { url 'http://raw.github.com/yuishihara/SoundPoolWrapper/master/repository/' }
}

dependencies {
    compile 'jp.techblog.epsiloncode:soundpoolwrapper:0.0.1'
}
```

## License

[Apache Software Licence 2.0](http://www.apache.org/licenses/LICENSE-2.0)
