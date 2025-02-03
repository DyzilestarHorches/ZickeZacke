# ZickeZacke

ZickeZacke is a 3D board game developed using the LibGDX framework, created as an academic project. The game replicates the well-known children's board game Zicke Zacke Hühnerkacke, adding interactive 3D elements and game logic.

##Features:
3D board and characters
Interactive game engine
Multiplayer support
##Tech Stack:

- Java
- LibGDX
- The NClib in core/src/com/zickezacke/nclib is a library that simulates Unity behavior and supports gameobject (2D and 3D), texture, textObject, interactions with 2D & 3D, components, and scene management.

##Run the game:
Get the release.rar file from the root of the project and extract it.

Then run the game using this command:

```
java -jar ./zicke-zacke.jar
```

##Setup:
Clone the repository: git clone https://github.com/nguyenbhchuong/ZickeZacke.git

For Windows:

```
#Navigate to project directory
./gradlew.bat desktop:run
```

For Mac:

```
#Navigate to project directory
chmod +x gradlew  # Make the gradle wrapper executable
./gradlew desktop:run
```

- The desktop launcher configuration is defined here:

```
public class DesktopLauncher {
	public static void main (String[] arg) {
		//ZickeZacke game = new ZickeZacke();
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Zicke Zacke");
		config.setWindowIcon("./UI/logo.png");
		config.setResizable(false);
		//config.setWindowedMode(1920, 1080);
		//config.setWindowedMode(1280, 720);
		config.setWindowedMode(1600, 900);

		new Lwjgl3Application(ZickeZacke.getInstance(), config);
	}
}
```

##License:
MIT License
