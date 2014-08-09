Buildhelper
===========


The purpose of this mod is to automate many of more tedious tasks in
the game, especially as relates to large structure building and clearing
and teraforming of landscapes. Many of the tools will be similar to
WorldEdit, though limited in scope to what can be done by the client.
  
  Features:
  
  Selection
   Set the selection area .. done
   Adjust the area .. done
   Set to block .. done
   Replace block with block .. done
   Overlay block on first non-air block .. done
   Stack/repeat blocks in selection in a direction .. done
     Allow stacking in combined/multiple directions .. done
   Smooth blocks
   Set block in shape
     Fill/hollow:
       Ellipsoid/sphere
       Walls (around edges of selection)
       Cuboid
       Pyramid
       Cylinder
       Triangular prism
   Fill hole. Might be complex with liquids, needing extra blocks placed/removed to be placed against
   Render selection points in world .. done
   Render "changelist" (queued block changes) in the world .. done
  
  Wand
   Set selection points .. done
   Use brushes to place/remove at a distance
     Should also have option for placing on top of queued blocks
  
  Clipboard
   Save/load to .schematic
   Copy/cut blocks in selection
   Paste blocks (relative to player, not selection)
   Rotate/flip contents of clipboard
   Render clipboard in world (holographic?)
  
  Project ?
   Something that allows the saving off current block queues, to allow for work over time on large change (ie schematics)
   Might be similar to the "shapes" in jarhyn's shapebuilder
  
  
Buglist
    Pistons and Dispensers won't get placed in up/down position properly. This is because the game calculates whether they
        should be in this position based on relative player position, not player look direction. Needs special case
    If a block is set to a two block wide/tall block (bed, door), extra block space won't be cleared
    Stack/copying redstone, the manipulator checks the powered/unpowered stack of placed block, and can get in an infinite
        place/remove loop if that state doesn't match up.
 