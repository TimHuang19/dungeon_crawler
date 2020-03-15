# Dungeon Crawler

## Overview

An application for the playing of dungeon-style puzzles. The user moves a player around a dungeon and try to overcome various challenges in order to "complete" the dungeon by reaching some goal. The simplest form of such a puzzle is a maze, where the player must find their way from the starting point to the exit while the hardest levels will involve very fast enemies chasing
them while having to accomplish multiple other tasks at the same time.

##  Screenshots

The title screen

![Title][title]

The first level, a basic maze

![Maze][maze]

More advanced puzzles may contain things like boulders that need to be pushed onto floor switches,

![Boulders][boulders]

or enemies that need to be fought with weapons, potions, or treasure.

![Advanced dungeon][advanced]

Complex levels will have multiple goals that will need to be completed. These are listed to the side and will turn green upon completion

![Goals][goals]

## How To Use
The project was created in Eclipse. Once the project is imported into Eclipse the application can be booted by running the src/unsw/dungeon/DungeonApplication.java

### Dungeon layout

The layout of each dungeon is defined by a grid of squares, each of which may contain one or more entities. The different types of entities are as follows:

| Entity               | Example | Description                             |
| ------               | ------- | --------------------------------------- |
| Player               | ![Player][player] | The player can be moved either up, down, left, or right into adjacent squares, as long as another entity does not stop them (e.g. a wall). |
| Wall                 | ![Wall][wall] | Blocks the movement of the player, enemies and boulders. |
| Exit                 | ![Exit][exit] | If the player goes through an exit the puzzle is complete.  |
| Treasure             | ![Treasure][treasure] | Can be collected by the player. |
| Door                 | ![Door][door_open] ![Door][door_closed] | Exists in conjunction with a single key that can open it. If the player holds the key, they can open the door by moving through it. After opening it remains open. The client will be satisfied if dungeons can be made with up to 3 doors. |
| Key                  | ![Key][key] | Can be picked up by the player when they move into the containing square. The player can carry only one key at a time, and only one door has a lock that fits the key. The key disappears once it is used to open its corresponding door. |
| Boulder              | ![Boulder][boulder] | Acts like a wall in most cases. The only differences are that it can be pushed by the player into adjacent squares and can be destroyed by a bomb. The player is only strong enough to push **one** boulder at a time. |
| Floor switch         | ![Floor switch][switch] | Switches behave like empty squares so other entities can appear on top of them. When a boulder is pushed onto a floor switch, it is triggered. Pushing a boulder off the floor switch untriggers it. |
| Unlit bomb           | ![Unlit bomb][bomb_unlit] | The bomb is picked up by the player when they move into the square containing it. |
| Lit bomb             | ![Lit bomb][bomb_lit_1] ![Lit bomb][bomb_lit_2] ![Lit bomb][bomb_lit_3] ![Lit bomb][bomb_lit_4] | The player can light the bombs they have collected and drop them. The fuse burns down for a short fixed period of time before the bomb explodes. Upon explosion, any boulders or enemies in the squares immediately to the left, right, above or below are destroyed. If the player is in one of these squares they die. |
| Enemy                | ![Enemy][enemy] | Constantly moves toward the player, stopping if it cannot move any closer. The player dies upon collision with an enemy. |
| Sword                | ![Sword][sword] | This can be picked up the player and used to kill enemies. Only one sword can be carried at once. Each sword is only capable of 5 hits and disappears after that. One hit of the sword is sufficient to destroy any enemy. |
| Invincibility potion | ![Invincibility][invincibility] | If the player picks this up they become invincible to all bombs and enemies. Colliding with an enemy should result in their immediate destruction. Because of this, all enemies will run away from the player when they are invincible. The effect of the potion only lasts a limited time. |
| Hound | ![Hound][hound] | A faster type of enemy that is immune to swords. Has same behaviour as enemy. |
| Telepad | ![Telepad][telepad] | If the player picks walks onto a square containing a telepad and uses it they will teleport to a corresponding telepad. Telepads are two-way and can be used an unlimited amount of times. |

### Goals

In addition to its layout, each dungeon also has a goal that defines what must be achieved by the player for the dungeon to be considered complete. Basic goals are:

* Getting to an exit.
* Destroying all enemies.
* Having a boulder on all floor switches.
* Collecting all treasure.

More complex goals can be built by logically composing basic goals. For example,

* Destroying all enemies AND getting to an exit
* Collecting all treasure OR having a boulder on all floor switches
* Getting to an exit AND (destroying all enemies OR collecting all treasure)

If getting to an exit is one of a conjunction of conditions, it must be done last. For example, if the condition is to destroy all enemies AND get to an exit, the player must destroy the enemies *then* get to the exit.

### Input

The application will read from a JSON file containing a complete specification of the dungeon (the initial position of entities, goal, etc.).

The dungeon files have the following format:

> { "width": *width in squares*, "height": *height in squares*, "entities": *list of entities*, "goal-condition": *goal condition* }

Each entity in the list of entities is structured as:

> { "type": *type*, "x": *x-position*, "y": *y-position* }

where *type* is one of

> ["player", "wall", "exit", "treasure", "door", "key", "boulder", "switch", "bomb", "enemy", "sword", "invincibility"]

The `door` and `key` entities include an additional field `id` containing a number. Keys open the door with the same `id` (e.g. the key with `id` 0 opens the door with `id` 0).

The goal condition is a JSON object representing the logical statement that defines the goal. Basic goals are:

> { "goal": *goal* }

where *goal* is one of

> ["exit", "enemies", "boulders", "treasure"]

In the case of a more complex goal, *goal* is the logical operator and the additional *subgoals* field is a JSON array containing subgoals, which themselves are goal conditions. For example,

```JSON
{ "goal": "AND", "subgoals":
  [ { "goal": "exit" },
    { "goal": "OR", "subgoals":
      [ {"goal": "enemies" },
        {"goal": "treasure" }
      ]
    }
  ]
}
```

Note that the same basic goal *can* appear more than once in a statement.

[player]:        images/human_new.png
[wall]:          images/brick_brown_0.png
[exit]:          images/exit.png
[door_open]:     images/open_door.png
[door_closed]:   images/closed_door.png
[key]:           images/key.png
[boulder]:       images/boulder.png
[switch]:        images/pressure_plate.png
[bomb_unlit]:    images/bomb_unlit.png
[bomb_lit_1]:    images/bomb_lit_1.png
[bomb_lit_2]:    images/bomb_lit_2.png
[bomb_lit_3]:    images/bomb_lit_3.png
[bomb_lit_4]:    images/bomb_lit_4.png
[enemy]:         images/deep_elf_master_archer.png
[sword]:         images/greatsword_1_new.png
[invincibility]: images/brilliant_blue_new.png
[treasure]:      images/gold_pile.png
[hound]:         images/hound.png
[telepad]:       images/telepad.png

[maze]:          examples/maze.png
[boulders]:      examples/boulders.png
[advanced]:      examples/advanced.png
[title]:         examples/title.png
[goals]:         examples/goals.png
