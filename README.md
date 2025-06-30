## This took about a week to get to version **1**, or about *15-20* hours to make.

### This project is a *loose* re-creation of [Fundy's baby mode](https://www.youtube.com/watch?v=Hld36cKKcm0) but there are some features that I added and changed that I think make it better.

The first thing I did to start creating the mod was learning a mod loader.
I did a lot of Minecraft modding (just installing mods) in the past, but I've never developed a mod before.
I used to only play with Curseforge mods, but eventually switched to Fabric when it was more popular.
I still didn't really like Fabric with how many cool mods it was missing from Curseforge, but doing a little research, I found Fabric was the easiest to develop on.
So, I started watching [Kaupenjoe's Fabric Modding Tutorial](https://www.youtube.com/watch?v=bVho57E_1hU&list=PLKGarocXCE1H_HxOYihQMq0mlpqiUJj4L).
I got to about the 5th episode before I stumbled along the Fabric documentation (and actually read it) and realized the mod that I wanted to make was actually pretty simple.
So, most of the mod is written from the Fabric docs and some Ai (when the Fabric docs didn't work or I couldn't find what I was looking for).

This brings me to:
# The first feature (v0.1)
I was still really new to version control at the time, and every feature I added took a while to implement.
That's why my first feature was all "pickaxes are enchanted" and not every other tool as well. Pretty self explanatory, if I do say so my self.
All I had to do was create a .json file in my mod. It was located in resources\data\minecraft\recipe\[insert_pickaxe_here]_pickaxe.json
and I just gave it a component called "enchantments", and looked up every enchantment a pickaxe could have, and made every enchantment level ten.

# The second feature (v0.1.4)
Again, as I said before, I was new to version control, so the second feature is the same as the first one, but with shovels.

# The third feature (v0.1.5)
This third feature is the same as the second and first feature, but with axes.

# The fourth feature (v0.1.6)
This fourth feature is the same as the third, second, and first feature, but with swords.

# The fifth and final enchant feature (v0.1.7)
Finally, I started to get a feel for version control after this so the updates would be more feature-rich.
This feature was the same as every feature so far, but with hoes.

# The sixth feature (v0.2)
This is the first actual Java that I wrote.
This feature allows you to, first, break trees without your fists.
I thought that if you were a baby, that might hurt. So I made it so you can "intimidate" the tree by walking right up to it, and staring at it right in the eye.
It will cripple in your intimidation and break. No need for any physical labor!
The second feature in this update, is whenever you go in water, you get water breathing. That way, if you get stuck under the water, you'll never die!

# The seventh feature (v0.2.1)
This update has less features, but it took a while to figure this one out.
It detects if there's a block at the player's head's coordinates, and replaces it with air if it isn't air.
This was a bit challenging to do because there could be a lot of blocks that could technically be at the player's head.
Torches, tall grass, ladders, etc.

# The eighth feature (v0.2.2)
This update was pretty simple. To get rid of hunger, 
all I had to do was check to see if the player's current health was less than their max health. If so, give saturation.

# The ninth feature (v0.2.3)
This update was a doozy. To get the mobs to not be able to attack the player, I had to make a box around the player, and check if there are any entities in that box. If there were, I had to check to see if they were hostile. If so, I would create a string variable that would kill the mob.

# The tenth feature (v0.2.4)
This feature was pretty easy. To get the player not be able to die in lava, I checked to see if the player's feet were in lava. If so, apply fire resistance. Very similar to the water feature.

# the eleventh feature (v0.2.4.1)
This was my first bug patch. I forgot to add the spruce log to the v0.2 update, so I just added it to the list.

# the twelfth feature (v0.2.4.2)
This was another bug patch. I forgot that in v0.2.4 that lava isn't the only thing that could set the player on fire. So I changed the if statement from, "asking if lava is at the players feet" to "if the player is on fire." 

# the thirteenth feature (v0.2.4.3)
I felt like the mod was a bit boring and was a bit too serious, so I added a sign to be placed after the mob dies that says, "{mob} died to: your existence". This was an unexpectedly hard feature because for some reason, the mob would move 2 blocks before being killed so there would be 2-3 signs that would be placed. I had to add one more command. It was making the entity have no Ai by merging the data. That way it wouldn't move when the sign would be placed. Then it would place the sign, and afterward kill the entity.

# the fourteenth feature (v0.2.4.4)
This update was very small. All I did was make it so every time you load into the world it sets commandFeedback to "false" so the command outputs don't fill up the chat.

# the fiveteenth feature (v0.2.5)
This update was a lot of trial and error. The actual concept is pretty simple. If the player is in the air, apply the slow falling effect. It wasn't actually that easy. I figured out that sprint jumping isn't optimal with slow falling so I had to make it be applied for a very short amount of time **and** have it not be applied unless you actually will take fall damage. I figured out you can get the player's Y-velocity and after a lot of trial and error, I figured out -0.66 was the max velocity you can have before taking fall damage.

# the sixteenth feature (v0.2.5.1)
This update was really small. I just made it so you get dolphin's grace and night vision when you enter the water. (improved feature from v0.2)

# the seventeenth feature (v0.2.6)
This was actually easier than I thought, but took more time than expected. All I had to do to make stone drop ore was to create a json file called stone.json and give it a pool. I created 2 pools. One that is guaranteed to give cobblestone, and another pool to give a chance to drop an ore.

# the eighteenth feature (v0.2.6.1)
This update is probably the smallest I've done so far. All I did was tweak the chances of you getting ore from stone.

# the nineteenth feature (v0.2.7)
This is like v0.2.4.4 but it runs the command keepInventory true instead of sendCommandFeedback.

# the twentieth feature (v0.3)
I meant for v0.3 to be for me to be working on the nether, but then I realized I was missing features so I accidentally jumped to v0.3.
This update makes it so every effect that you get is infinite (if it's a positive effect). I still left out feather falling though, because of the problems listed earlier in v0.2.5.

# the twenty-first feature (v0.3.1)
This update was a bit bigger than others, but that was also because I didn't want to mess up my versioning. I made it so every armor has max enchants, and made it so diamond ore drops a bunch of diamonds and has a chance of dropping netherite. I felt badly for someone if they actually went mining so I left a big suprise if they found diamonds.

# the twenty-second feature (v0.3.1.1)
This is my first (technically) nether update. I made it so every obsidian drops one stack of obsidian when broken.

# the twenty-second feature (v0.3.2)
This is my actual first nether update. I used the existing boundary from v0.2.3 and made it ask if any lava is in that radius as well. If so, it will turn it to obsidian.

# the twenty-third feature (v0.3.3)
This update made it so when you enter the end for the first time, you'd get 16 ender pearls, 64 eyes of ender, and 64 blaze rods. That way you don't have to go find a fortress.

# the twenty-fourth feature (v0.3.4)
This update tweaks the drops of enderman, blazes, and wither skeletons (these were json files so they work in every dimension). The blaze always drops 8 or more blaze rods, (same with the enderman but with pearls), and the wither skeleton always drops its head.

# the twenty-fifth feature (v0.3.5)
This update is like v0.3.1 with the diamond ore, but with netherite ore. Except, it doesn't have a chance of dropping diamonds. Only guaranteed netherite, and a chance for bonus netherite.

# the twenty-sixth feature (v0.3.5.1)
This update adds messages sent in the chat when you enter the nether for the first time.

# the twenty-seventh feature (v0.6)
I messed up the versioning. I didn't realize how far ahead my features were compared to my version number. I guess this is why you should use a version road map. Anyway, this update makes it so that when you get within 5 blocks of the ender dragon, it will say something, and die. This allows you to skip the fight.

# the twenty-eighth feature (v0.8)
This update was a bit hard, because when I initially implemented the feature, when you fell into the void, it would teleport you back up, but it would reset your camera position. So the real challenging part was preserving the player's camera position (especially in the end where it's easy to get lost).

# the twenty-ninth feature (v0.9)
This feature makes it so when you get an elytra, if you put it in your main hand, it will enchant it with mending, and unbreaking ten.

# the thirtieth feature (v1)
Suprise suprise, the final update was a bug fix. When implementing the enchanted elytra update, I couldn't get to an end city in survival, because the ladder kept breaking on me. So, I added an exception to torches and ladders.

## This was cs50x/babymode.
