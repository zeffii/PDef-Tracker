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

~repeat_str = { |str, num_times|
    var empty_str = "";
    num_times.do {
        empty_str = (empty_str ++ str);
    };
    empty_str;
};

~cursor_cell = [0, 0];
~cursor_subcell = 0;
~cursor_highlight = {
    | keycode, modifiers, num_tracks, num_rows |

    ~chars = 13;  // per_cell
    switch (keycode,
        39, {// move right one subcell or entire cell with shift modifier
            if (modifiers.isShift,
                {~cursor_cell[0] = ~cursor_cell[0] + 1 },
                {~cursor_subcell = (~cursor_subcell + 1) % ~chars })  },

        37, {// move left one subcell or entire cell with shift modifier
            if (modifiers.isShift,
                {~cursor_cell[0] = ~cursor_cell[0] - 1 },
                {~cursor_subcell = (~cursor_subcell - 1) % ~chars })  },

        38, { ~cursor_cell[1] = ~cursor_cell[1] - 1 }, // up
        40, { ~cursor_cell[1] = ~cursor_cell[1] + 1 }  // down
    );

    ~cursor_cell[0] = ~cursor_cell[0] % num_tracks;
    ~cursor_cell[1] = ~cursor_cell[1] % num_rows;



};
