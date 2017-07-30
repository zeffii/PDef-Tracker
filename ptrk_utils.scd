// a utility function file for ptrk systems

~keycode_to_note = {
    | keycode, octave |
    ~oct_str = octave.asString;
    ~oct_str_down = (octave - 1).asString;
    ~oct_str_up = (octave + 1).asString;
    switch (keycode,
        // upper row of keyjazz keys
        81, {"C-" ++ ~oct_str},  // q
        50, {"C#" ++ ~oct_str},  // 2
        87, {"D-" ++ ~oct_str},  // w
        51, {"D#" ++ ~oct_str},  // 3
        69, {"E-" ++ ~oct_str},  // e
        82, {"F-" ++ ~oct_str},  // r
        53, {"F#" ++ ~oct_str},  // 5
        84, {"G-" ++ ~oct_str},  // t
        54, {"G#" ++ ~oct_str},  // 6
        89, {"A-" ++ ~oct_str},  // y
        55, {"A#" ++ ~oct_str},  // 7
        85, {"B-" ++ ~oct_str},  // u
        73, {"C-" ++ ~oct_str_up},  // i
        57, {"D#" ++ ~oct_str_up},  // 9
        79, {"D-" ++ ~oct_str_up},  // o
        48, {"D#" ++ ~oct_str_up},  // 0
        80, {"E-" ++ ~oct_str_up},   // p
        // lower row
        90, {"C-" ++ ~oct_str_down},  // z
        83, {"C#" ++ ~oct_str_down},  // s
        88, {"D-" ++ ~oct_str_down},  // x
        68, {"D#" ++ ~oct_str_down},  // d
        67, {"E-" ++ ~oct_str_down},  // c
        86, {"F-" ++ ~oct_str_down},  // v
        71, {"F#" ++ ~oct_str_down},  // g
        66, {"G-" ++ ~oct_str_down},  // b
        72, {"G#" ++ ~oct_str_down},  // h
        78, {"A-" ++ ~oct_str_down},  // n
        74, {"A#" ++ ~oct_str_down},  // j
        77, {"B-" ++ ~oct_str_down},  // m
        188, {"C-" ++ ~oct_str},  // ,
        // specials
        192, {"==="},
        49, {"^^^"}
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
~cursor_position = { | keycode, modifiers, num_tracks, num_rows |

    ~chars = 13;  // per_cell
    switch (keycode,

        39, {// move right one subcell or entire cell with shift modifier
            if (modifiers.isShift,
                {~cursor_cell[0] = ~cursor_cell[0] + 1 },
                {
                    // flip to the cell to the right
                    if (~cursor_subcell==12, { ~cursor_cell[0] = ~cursor_cell[0] + 1},{});
                    ~cursor_subcell = (~cursor_subcell + 1) % ~chars })
        },

        37, {// move left one subcell or entire cell with shift modifier
            if (modifiers.isShift,
                {~cursor_cell[0] = ~cursor_cell[0] - 1 },
                {
                    // flip to the cell to the left to
                    if (~cursor_subcell==0, { ~cursor_cell[0] = ~cursor_cell[0] - 1},{});
                    ~cursor_subcell = (~cursor_subcell - 1) % ~chars })
        },

        38, { ~cursor_cell[1] = ~cursor_cell[1] - 1 }, // up
        40, { ~cursor_cell[1] = ~cursor_cell[1] + 1 }  // down
    );

    ~cursor_cell[0] = ~cursor_cell[0] % num_tracks;
    ~cursor_cell[1] = ~cursor_cell[1] % num_rows;


};

~subcell_idx_to_name = { |ndx|
    switch (ndx,
        0, {\NNN},
        2, {\DD},
        4, {\VV},
        6, {\AA},
        7, {\XXYY});
};

~keyboard_patternview_handler = { |view, pattern_matrix, keycode, modifiers|

    /*
    NNN DD VV AA|XXYY

    +---------------+-------------------------------------------------------------+
    | NNN: note     | if (subcell cursor is in position 0 or 1), then edit note   |
    |               | el if position 2: edit octave using numbers                 |
    |               | don't progress subcell cursor to the right                  |
    +---------------+-------------------------------------------------------------+
    |  DD: device   | if position of cursor subcell is 3, accept valid input      |
    |               | and move to position 4. if in 4 stay in 4 or progress down  |
    +---------------+-------------------------------------------------------------+
    |  VV: volume   | if position of cursor subcell is 5, accept valid ints and   |
    |               | progress to 6, if in 6 stay in 6 or progress down           |
    +---------------+
    |  AA: parameter|
    +---------------+
    |XXYY: value    |
    +---------------+-------------------------------------------------------------+
    */

    ~dx = case
    {~cursor_subcell < 2} {
        ~keyvalue = ~keycode_to_note.value(keycode, 4);

        if (~keyvalue.isString,
            {
                pattern_matrix[~cursor_cell[0],~cursor_cell[1]][\NNN] = ~keyvalue;
                view.refresh;
            });
    }
    {~cursor_subcell == 3} {                       }
    {[3, 4].includes(~cursor_subcell)} {                     }
    {[5, 6].includes(~cursor_subcell)} {                     }
    {[7, 8].includes(~cursor_subcell)} {                     }
    {[9, 10, 11, 12].includes(~cursor_subcell)} {                     };
};
