# Arbetsprov-Sectra
This program will estimate storage size for user specified images of specific types and dimensions. It currently supports the image types baseline jpeg, jpeg 2000 and BMP. The tool can also account for stacks of images, which will allow for more aggressive compression. 
It is used with one of the three following commands:
  * "type width height" where type is "J" or "JPG" for baseline jpeg, "JP2" or "JPEG2000" for jpeg 2000, and "BMP" for uncompressed bitmaps. Width and height should be any positive integer.
  * "g i1 i2 ... in" will create a stack of the images with indices 1, 2 ... up to n. An image cannot be a member of multiple groups.
  * "q" sums up the total estimated storage size and exits the program.
