s.boot;

(

GUI.qt;
Window.closeAll;

w = Window.new("ptrk", Rect.new(1140, 530, 760, 520))
    .front
    .alwaysOnTop_(true);

u = UserView(w, Rect(60,43, 750, 500))
    .backColor_(Color(0.9, 0.9, 0.9, 0.5));

~num_cols = 4;
~num_rows = 16;
~subcell_color = Color(0.6, 0.8, 0.88, 1.0);

/*

tab / shift-tab   |  jump to next / previous  cell
left / right      |  jump to next / previous  subcell

*/

u.drawFunc_{ |tview|

    ~num_rows.do { |idx |
        ~num_cols.do { | jdx |
            ~num_subcells { |vdx |

            }
        }
    }





)