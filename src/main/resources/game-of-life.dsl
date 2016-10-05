  when live with {neighbours == 2} then live
  when live with {neighbours == 3} then live
  when live with {neighbours < 2}  then dead
  when live with {neighbours > 3}  then dead
  when dead with {neighbours == 3} then live


