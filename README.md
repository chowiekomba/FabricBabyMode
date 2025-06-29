## This took about a week to get to v1, or about **15-20** hours to make so, let's hope the project is big enough for this readme to get to 750 words.

### This was a *loose* re-creation of [Fundy's baby mode](https://www.youtube.com/watch?v=Hld36cKKcm0) but there's some features that I added and changed that I think make it better.

The first thing I did to start creating the mod was learning a mod loader.
I did a lot of minecraft modding (just installing mods) in the past, but I've never developed a mod before.
I used to only play with curseforge mods, but eventually switched to fabric when it was more popular.
I still didn't really like fabric with how much cool mods it were missing from curseforge, but doing a little research, I found fabric was the easiest to develope on.
So, I started watching [Kaupenjoe's fabric modding tutorial](https://www.youtube.com/watch?v=bVho57E_1hU&list=PLKGarocXCE1H_HxOYihQMq0mlpqiUJj4L).
I got to about the 5th episode before I stumbled along the fabric documentation (and actually read it) and realized the mod that I wanted to make was actually pretty simple.
So, most of the mod is written from the fabric docs and some ai (when the fabric docs didn't work or I couldn't find what I was looking for).

This brings me to:
# The first feature (v0.1)
I was still really new to version control at the time, and every feature I added took a while to implement.
That's why my first feature was all pickaxes are enchanted. Pretty self explanatory if I do say so my self.
All I had to do was create a .json file in my mod. It was located in resources\data\minecraft\recipe\[insert_pickaxe_here]_pickaxe.json
and I just gave it a component called enchantments, and looked up every enchantment a pickaxe could have, and made every enchantment level 10.

# The second feature (v0.1.4)
Again, as I said before, I was new to version control, so the second feature is the same as the first one, but with shovels.

# The third feature (v0.1.5)
This third feature is the same as the second and first feature, but with axes.

# The fourth feature (v0.1.6)
This fourth feature is the same as the third, second, and first feature, but with swords.

# The fiveth and final enchant feature (v0.1.7)
Finally, I started to get a feel for version control after this so the updates should be more feature rich.
This feature was the same as every feature so far, but with hoes.

# The sixth feature (v0.2)
This is the first actual java I wrote.
This feature allows you to number one, break trees without your fists.
I thought that if you were a baby, that might hurt. So I made it so you can "intimidate" the tree by walking right up to it, and staring at it right in the eye.
it will cripple in your intimidation and break. No need for any physical labor!
The second feature in this update, is whenever you go in water, you get water breathing. That way, if you get stuck under the water, you'll never die!

# The seventh feature (v0.2.1)
This update has less features, but it took a while to figure this one out.
It detects if there's a block at the player's head's coordinates, and replaces it with air if it isn't air.
This was a bit challenging to do because there could be a lot of blocks that could technically be at the player's head.
Torches, tall grass, ladders, etc.
