# Adventure Time - A 2D Retro Game! 

This is a 2D adventure game and also my final bootcamp project. The intention of this game is to demonstrate what I have learned 
in the bootcamp, plus a couple more things I managed to learn on my own. This is not being updated anymore, however if anything comes to my mind, I will eventually add more monsters and even different maps. 

### Table of contents:
- How to Install
- How to Play
- Game Items/Techniques
- What I have learned with this project
- Future plans 
- Current bugs
- Screenshots of the game
	
### How to Install: 
- You can find the latest release of the game by downloading the game [**HERE**](https://drive.google.com/file/d/13Wdrt1MRlPjsFQGan15Z00OZBoIKymbJ/view?usp=sharing). You will need to have [**JDK installed in your system**](https://www.oracle.com/uk/java/technologies/downloads/).
	
### How to Play:
- Controls
	- Movement - **WASD**
	- Attack - **ENTER**
	- Parry/Defend - **SPACE**
	- Cast fireballs - **F**
	- Pause - **P**
	- Map - **M**
	- Minimap - **X**
	- Inventory - **C**


### Game Items/Techniques
Here are some important features and mechanics to help you navigate through your adventure:
1. **Guard:** Protect yourself from enemy attacks by blocking half the damage.

2. **Parry:** With precise timing, you can parry an enemy attack and gain a critical hit chance for one second, which doubles your damage output.

3. **Ninja:** As a skilled ninja, you can use your weapon to cut through incoming projectiles. However, timing is crucial in this technique.

4. **Survivor:** Your surroundings can be destroyed to aid in your battle. Choose your weapons wisely to maximize your destruction potential.

5. **Healing Pool:** Recover your life and mana by taking a dip in a nearby pool, but beware that monsters will respawn upon your return.

6. **Coins:** Visit the merchant and purchase stronger items to give yourself an edge in battle.

7. **Tent:** Rest and replenish your energy by sleeping until the next day.

8. **Lantern:** When the sun sets, the importance of your lantern becomes apparent. Keep it handy to navigate through the darkness.


The goal of the game? Solve the **dungeon puzzle**, kill the **final boss** and get the **treasure**. 
	
	
### What I have learned with this project:
- Create a game loop
- Display player character
- Display background tiles
- Display objects
- Create monsters (simple AI) and Battle System (hit detection)
- Pathfinding & Agressive Monsters
- Collision detection between NPC's and players
- Added an invincible state after hitting a monster
- Create battle system (also boss fights)
- Create NPCs & Dialogue system
- Create player inventory
- Create title screen & game state (pause game, game over screen & save/load game)
- Create Night & Day cycle
- Cutscenes
- Adding music and sound effects
- Merchant NPC
- Item Drops
- Switching the Lightning On/Off (Lantern)
- Stackable Items
- Guard & Parry
- Boss fight + health bar
- Cutting Projectiles & Knockback
- Adding particles

### Future plans:
- [x] Completed ~~This game is still being updated on a regular basis. I have a lot of other features I would love to include, including different maps,
different classes (fighter, mage, archer, etc), different monsters, different songs depending on the monster you're fighting, and even
different dimensions, environment interaction (cutting trees with an axe), In-game Menu and different maps (map transitions). However, all of these are not my current priority.~~
- [ ] Increasing the map and adding different monsters
- [ ] Possibly host the game online
- [ ] Add different monsters
- [ ] Give the boss a second phase (like souls games)
- [ ] Add different classes with different character sprites
- [ ] Add different dimensions (like the nether in Minecraft)

### Current bugs (TBC):
- [x] FIXED ~~There are no sound effects or music in the game yet. I added all the sound files but they are still not working. Will try to fix this as soon as possible~~
- [x] FIXED ~~Healing fountain location is slightly bugged. You need to be in the EXACT tile for it to work~~
- [x] FIXED ~~If you spam the healing fountain, you will get more health than you currently have (eg: if you have maximum health and you use the healing fountain, you get 3 extra hearts on top of that)~~
- [x] FIXED ~~Monsters deal damage when they're in their dying animation~~
- [ ] Attack animation is slightly out of focus 
- [ ] Attack range is slightly out of place

# Screenshots of the game:

**Start Screen**

> ![Start screen](https://user-images.githubusercontent.com/56265972/234721690-f9f65093-2ea1-4e7e-bffd-09f948faf6a4.png)


**Spawn of the game**

> ![Spawn of the game](https://user-images.githubusercontent.com/56265972/234721722-e2823a32-0454-4328-b3e1-19bfe66e2501.png)


**NPC Dialogue**

> ![NPC Dialogue](https://user-images.githubusercontent.com/56265972/234721937-aa62c03b-f663-46be-a876-0e993454239f.png)


**Slime monsters**

> ![Slime monsters](https://user-images.githubusercontent.com/56265972/233201107-88ea13ad-e633-4ef1-b439-f69fce9d4eb6.png)


**When damage has been taken from the monsters, the character gets 1 second invincible state**

> ![When damage has been taken, character gets 1 second invincible state](https://user-images.githubusercontent.com/56265972/233201943-3f0e1784-99ee-4e03-90d6-4670ca3a7689.png)


**Stats menu**

> ![nGwF7knrQr](https://user-images.githubusercontent.com/56265972/234721862-a631253d-3516-435b-bb9b-71abdaf9d517.png)


**Traps across the map**

> ![Traps across the map](https://user-images.githubusercontent.com/56265972/233201709-24810488-ceaa-44ae-941b-cafcf207bf6e.png)


**Healing fountain**

> ![Healing water](https://user-images.githubusercontent.com/56265972/234721762-ae97393c-d4da-4a19-bbf5-271ec9f57d1e.png)


**Parry system**

> ![parry](https://user-images.githubusercontent.com/56265972/234723991-5246375b-2b1a-41c2-8171-297df8ebf05b.png)


**Night & Day**

>![night](https://user-images.githubusercontent.com/56265972/234724092-4b5d99f8-bf7d-4b10-9417-098df4f94fe3.png)


**Merchant NPC**

> ![merchant](https://user-images.githubusercontent.com/56265972/234724159-8add2e09-4be7-47dc-90b0-61b1ad66ec73.png)


# Special Thanks
Thank you to RyiSnow for providing the world tiles and npc sprites, and Game Endeavor for the character sprites. This wouldn't have been possible without their support.
