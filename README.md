
# react-native-mancas-snowboy

## Getting started

`$ npm install react-native-mancas-snowboy --save`

### Mostly automatic installation

`$ react-native link react-native-mancas-snowboy`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-mancas-snowboy` and add `RNMancasSnowboy.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNMancasSnowboy.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNMancasSnowboyPackage;` to the imports at the top of the file
  - Add `new RNMancasSnowboyPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-mancas-snowboy'
  	project(':react-native-mancas-snowboy').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-mancas-snowboy/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-mancas-snowboy')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNMancasSnowboy.sln` in `node_modules/react-native-mancas-snowboy/windows/RNMancasSnowboy.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Mancas.Snowboy.RNMancasSnowboy;` to the usings at the top of the file
  - Add `new RNMancasSnowboyPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNMancasSnowboy from 'react-native-mancas-snowboy';

// TODO: What to do with the module?
RNMancasSnowboy;
```
  