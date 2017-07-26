// a utility function file for ptrk systems

~keycode_to_note = {
    | keycode, octave |
    switch (keycode,
        // upper row of keyjazz keys
        81, {"C-" ++ octave.asString;},  // q
        50, {"C#" ++ octave.asString;},  // 2
        87, {"D-" ++ octave.asString;},  // w
        51, {"D#" ++ octave.asString;},  // 3
        69, {"E-" ++ octave.asString;},  // e
        82, {"F-" ++ octave.asString;},  // r
        53, {"F#" ++ octave.asString;},  // 5
        84, {"G-" ++ octave.asString;},  // t
        54, {"G#" ++ octave.asString;},  // 6
        89, {"A-" ++ octave.asString;},  // y
        55, {"A#" ++ octave.asString;},  // 7
        85, {"B-" ++ octave.asString;},  // u
        73, {"C-" ++ (octave + 1).asString;},  // i
        57, {"D#" ++ (octave + 1).asString;},  // 9
        79, {"D-" ++ (octave + 1).asString;},  // o
        48, {"D#" ++ (octave + 1).asString;},  // 0
        80, {"E-" ++ (octave + 1).asString;}   // p
    )
};
