# TeamBlue
Team Blue

Everyone: We all contributed to the controller and application files of our program. We worked together and figured out the combo box and how to apply each filter to our image.

Noah: I worked on the blur filter for our project. The blur filter works by iterating through all pixels in the image. It starts in the upper left corner and works its way to the bottom right corner. For each pixel, I look at the neighboring pixels in a 5x5 square (radius 2), and I find the average red, blue, green, and alpha values of the neighboring pixels. Then, I apply the averages to the respective pixel in the new image. 
https://datacarpentry.github.io/image-processing/06-blurring.html 

Jonathan: I made the gameboy filter. The game boy filter finds the brightness of all pixels by adding the RGB values and dividing by 3. Then the colors are assigned 1 of 4 green pixels from the original Nintendo Game Boy.
https://www.color-hex.com/color-palette/45299

Lawrence:I made the mirror filter. It works by reading in the original image using PixelReader, letting it access the color of each pixel. Then the new writable image is created with the same dimensions to create the mirror effect. The method then loops through every pixel, placing each color at its mirrored position. To get a better understanding of the mirror effect I watched a youtube tutorial on JavaFX image processing which helped me get the concept of pixel data.
https://www.youtube.com/watch?v=4z_WngselQ4

Symphony: I made the sepia tone filter. This filter works by changing the original pixels and changing the amount of red, green, and blue by contributing a weighted sum to the new value. 
