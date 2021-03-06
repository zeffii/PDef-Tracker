# PDef-Tracker

Tracker implementation for Supercollider 3.x

## 

Based loosely on ImpulseTracker, the ultimate tracker. This attempt is inspired by the frustration I face when using newer and cooler trackers like Buzz/buze/sunvox/renoise. I need something that's entirely controllable via keyboard, or at least most of the major editing/jazzing around features should not require mouse jockeying. I want to be able to script patterns or track them, or mix the ratio of either. I don't need it to look like a modern DAW, it can be butt ugly. I do need to be able to extend / modify fundamental features, and not rely on doing feature requests that may or may not ever be listened to (fuck that!).

Don't get excited yet, none of this works. If you can still read this message, then it's not even usable yet. I also don't know SuperCollider language intimately yet - that being said UI coding is coming along nicely.

Here's the todo:

**Milestone 0**

- [x] make pattern UI navigatable
- [x] make data editable (UI and storage issue)
- [x] highlight pattern data according to function
- [x] make pattern data enterable

**Milestone 1**

- [x] make sample display UI
- [x] make sample bank storage
- [x] make sample bank loader ui
- [ ] make basic sampler synthdef
- [ ] make basic sampler synthdef *UI*
- [ ] make pattern data composable to a Pattern structure
- [ ] make sample amp ADSR 
- [ ] make sample filter ADSR 
- [ ] make sample panning ADSR 
- [ ] make sample trigger pads (keyboard)

**Milestone 2**

- [ ] represent pattern notes in pianoroll like ui
- [ ] make synthdef/unit loader/bank
- [ ] make node tree view
- [ ] allow hot routing of units

**Milestone 3**

- [ ] ptrk console area, add commands for 
    - [ ] connecting / disconnecting
    - [ ] loading units
    - [ ] loading samples
    - [ ] rerouting units
    - [ ] muting tracks, (single, multiple)
- [ ] 8 bit mask generator
- [ ] 16 bit mask generator, (8 bit repeater with random bitrot on repeated)

If it gets this far more will come.

# Current Progress

See https://github.com/zeffii/PDef-Tracker/issues/1

