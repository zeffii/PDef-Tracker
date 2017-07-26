s.boot;

(

~repeat_str = { |str, num_times|
    var empty_str = "";
    num_times.do {
        empty_str = (empty_str ++ str);
    };
    empty_str;
};

~subcell_string_color = { |ndx|
    switch (ndx,
        6, {Color(0.78, 0.97, 0.77, 1.0)},
        7, {Color(0.53, 0.73, 0.93, 1.0)},
        {Color(0.98, 0.97, 0.97, 1.0)}
    );
};

~cell_darker = Color(0.5, 0.8, 0.9, 1.0);
~cell_dark = Color(0.32, 0.82, 0.92, 1.0);
~cell_light = Color(0.84, 0.84, 0.84, 1.0);
~cell_colors = [~cell_darker, ~cell_dark, ~cell_light];

~ui_font = Font("Consolas", 10);

// pattern variables
~num_cols = 4;
~num_rows = 16;
~subcell_color = Color(0.6, 0.8, 0.88, 1.0);
~cell_x_offset = 4;
~cell_y_offset = 2;
~patternview_font = Font("Fixedsys", 13);
~p_offset_x = 60;
~p_offset_y = 43;

// cell variables
~split = 0.36;
~subcells = [3, ~split, 2,~split, 2, ~split, 2, 4];
~char_width = 8;
~cell_height = 16;
~cell_width = (~subcells*~char_width).sum;

/*

tab / shift-tab   |  jump to next / previous  cell
left / right      |  jump to next / previous  subcell
up / down         |  updown cell, retaining subcell position.

*/


GUI.qt;
Window.closeAll;

w = Window.new("ptrk", Rect.new(1140, 530, 760, 520))
    .front
    .alwaysOnTop_(true);
w.view.backColor_(Color(0.13, 0.78, 0.9, 1.0));

u = UserView(w, Rect(~p_offset_x, ~p_offset_y, 750, 500))
    .backColor_(Color(0.72, 0.82, 0.89, 1.0));

// pattern and song info
~mu = UserView(w, Rect(~p_offset_x, 0, 200, ~p_offset_y-16));
~mu.backColor = Color(0.13, 0.3, 0.5, 0.07); // Color.clear;

~mu.drawFunc_{ |tview|
    ~info_name_width = 60;
    ~font_rescale = 0.8;
    ~mutv = StaticText(~mu, Rect(0, 0, ~info_name_width, ~cell_height*~font_rescale));
    ~mutv.string_("Song Name");
    ~mutv.align = \right;
    ~mutv.font = ~ui_font;
    ~mutv.stringColor = Color(0.9, 0.9, 0.9, 1.0);
    // ~mutv.background = Color(0.4, 0.4, 0.4, 1.0);

    ~mutp = StaticText(~mu, Rect(0, ~cell_height*~font_rescale, ~info_name_width, ~cell_height*~font_rescale));
    ~mutp.string_("Pattern");
    ~mutp.align = \right;
    ~mutp.font = ~ui_font;
    ~mutp.stringColor = Color(0.9, 0.9, 0.9, 1.0);
    // ~mutp.background = Color.black;
};

u.drawFunc_{ |tview|

    ~num_rows.do { |idx |
        ~cell_color = ~cell_colors[((idx % 4) < 1).asInteger];
        ~num_cols.do { | jdx |
            var xpos, ypos;
            xpos = jdx * (~cell_x_offset + ~cell_width );
            ypos = idx * (~cell_y_offset + ~cell_height );

            ~subcellx = 0;
            ~subcells.do { |vdx, ndx |
                ~text_rect = Rect.new(~subcellx + xpos, ypos, vdx*~char_width, ~cell_height);

                if (vdx > ~split, {
                    ~tv = StaticText(u, ~text_rect);
                    ~tv.string = ~repeat_str.value(".", vdx);
                    ~tv.align = \left;
                    ~tv.stringColor = ~subcell_string_color.value(ndx);
                    ~tv.font = ~patternview_font;
                    ~tv.background = ~cell_color
                },{});

                ~subcellx = (~subcellx + (~char_width * vdx));
            };
        };
    };

u.keyDownAction = {
    arg view, char, modifiers, unicode, keycode;
    // [keycode].postln; //, modifiers, unicode].postln;
    // ~cursor_highlight.value(keycode);
    // u.refresh;
};

// caret
~xu = UserView(u, Rect(0, 0, ~char_width, ~cell_height));
~xu.backColor = Color(1.0, 0, 0, 0.2);


}

);
