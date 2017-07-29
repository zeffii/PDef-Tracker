~subcell_string_color = { |ndx, cellstr|
    switch (ndx,
        4, {Color.fromHexString("FFDDD1")},
        6, {Color(0.78, 0.97, 0.77)},
        7, {

            if (cellstr != "....", 
                { Color(0.38, 0.52, 0.98)},
                { Color(0.53, 0.73, 0.93)})
        },
        {Color(0.98, 0.97, 0.97, 1.0)} // default
    )


};
