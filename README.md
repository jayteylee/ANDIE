## Context
Andie is an image editing program. 'Andie' stands for A-non-destructible-image-editing program. This means that all changes made to an image (greyscale, blurring, etc.) can be undone. The edited image is output with an extra file that stores the operations applied on the image. 

## Github repo
The repository can be cloned from https://altitude.otago.ac.nz/cosc202-vscoders/andie.git

## Compiling and running
The main class for this project is Andie.java inside of the cosc202.andie package.

---


## Filters

***Sharpen Filter:*** Jack

Accessed by: Filter menu (Sharpen filter option) and keyboard shortcut ‘P’.

The filter was tested on images of different dimensions. Some strange noise occurs when using the filter a number of times in combination with other filters. Comparing this to other sharpen filter convolutions online, they have the same noise pattern. This shows that it is a limitation of the implementation and not a bug. The filter has a clearly sharpening effect when applied to an image.

----

***Median Filter:*** Jay

Accessed by: Filter menu (Median filter option) and keyboard shortcut 'N'

Tested on many images with different colour palettes and sizes.

No formal testing framework was used but an image with randomly scatted chromatic aberrations was used to test whether the median filter effectively removed these spots while blurring the image. The Median filter now works on the edges of the image.

The current implementation of the filter is known to be very slow when run with radius values of above 4.

----

***Gaussian Blur:*** Daniel
Accessed by: Filter menu (Gaussian blur option); 
Keyboard shortcut ‘G’.
Tested on different images with different colours and sizes. One of the issue I face is when we apply the Gaussian blur filter to an image, the pixels of the image will be shifted slightly upwards. 

----

***Brightness & Contrast:*** Jake 

Accessed by : Colour menu (brightness and contrast option); Keyboard shortcut 'C'

Tested on many images with different colour palettes and sizes. Did some more in depth pixel testing by using a 3x3 pixel image and checking the calculations on the pixels. From this I found that there was some rounding error due to java int division, which was then fixed. Testing on a greyscale image showed some strange behaviour, the lightest values turning yellow. This was fixed with a lower bound converting anything below zero to zero. However I am unsure why the colour would turn yellow as all the different rgb channels in a greyscale image should be flattened. 

----

***Posterize***: Jack

Accessed by: Colour menu (Posterize option)

The user has an option to choose the colour bands used during the posterizing. A modal window opens and the user can select colors in the form of A-R-G-B in a colour chooser. Clicking okay in the colour chooser adds the colour to the list.

----
***Emboss and Sober***: Daniel

Accessed by: Emboss menu (Emboss options and Sober options); Keyboard shortcut '0 - 9'

The user has different emboss options to choose from based on the direction of simulation that they want to apply to. There are 8 different directions that they can choose from, and another 2 options where they can choose to do it vertically or horizontally.

----

## Tranformation operations

***Resize:*** Jack
Accessed via Image menu and toolbar. Shortcut ‘Ctrl + R’.

Resize was tested manually on a range of different sizes. Resizing to smaller or larger sizes produces a resized image with satisfactory results. Negative values make no change to the image.

----

***Rotate Image Clockwise:*** Jay

Accessed by: Image menu and keyboard shortcut 'Ctrl + ]'

No formal testing framework was used but a variety of images with different colour palettes and sizes were used to test the implementation of the function.

***Rotate Image Anti-Clockwise:*** Jay

Accessed by: Image menu and keyboard shortcut 'Ctrl + ['

No formal testing framework was used but a variety of images with different colour palettes and sizes were used to test the implementation of the function.

----

***Flip vertically*** Daniel
Accessed by: Image menu (Flip images vertically option); Keyboard shortcut: 'Shift + ['
No formal testing was used, just test the transformation with different images of different sizes to make sure the image are flipped properly.

----

***Flip horizontally*** Daniel
Accessed by: Image menu (Flip images horizontally option); Keyboard shortcut: 'Shift + ]'
No formal testing was used, just test the transformation with different images of different sizes to make sure the image are flipped properly.

----

***Image Export:*** Jake 

Accessed by: File menu(export option); Toolbar (rectangle with an arrow pointing right); Keyboard shortcut CTRL E.

Testing involved checking what happened with or without the inclusion of file extensions. It will now default to outputting a .jpg file if the user does not add a file extension to their file name.

----

## Mouse operations
***Mouse Selection*** Jake

All the mouse selection is run through the CustomListener class. When running it will do various tasks based on what the current operation is. The operation will start once the mouse has entered the panel and the mouse has been pressed, it will then continue while the user is dragging the mouse and end when the mouse press is released.
____
***Crop Selection:*** Jay
Accessed by: Image menu (Crop Selection option); Keyboard shortcut CTRL + C

No formal testing framework was used however different images and crops of different sizes were trialed to check whether the crop selection was behaving as expected.

----
***Drawing Operations*** Jake
Accessed by: Draw menu and keyboard shortcuts shown below

The operations include:

    * Drawing lines
    * Drawing empty rectangles
    * Drawing empty ovals
    * Drawing filled rectangles
    * Drawing filled rectangles
    * Free drawing - more in its own section
    * Colour selection panel

No formal testing framework was used however a lot of testing has been done with different shapes, sizes and mouse drag directions. Additionally undo/redo/save/export functionality has been tested. The drawn shapes should be accurate when the image is zoomed, though there seems to be some thickening of the lines.

There is also a custom selection panel by selecting the choose colour option in the draw menu. This will allow you to select a new colour in the HSV format.



### **Table of Keyboard Shortcuts:** Jay

| ***Function*** | ***Keyboard Shortcut*** |
| :--- | :---: | 
| Open | Ctrl + O | 
| Save | Ctrl + S | 
| Save as | Ctrl + Shift + S |
| Export | Ctrl + E |  
| Rotate Clockwise | Ctrl + ] |  
| Rotate Anti-Clockwise | Ctrl + ] | 
| Resize Image | Ctrl + R | 
| Flip Image Horizontally | Shift + ] | 
| Flip Image Vertically | Shift + [ |
| Crop Selection | Ctrl + C | 
| Undo | Ctrl + Z | 
| Redo | Ctrl + Y | 
| Zoom in | = | 
| Zoom in | - | 
| Zoom Full | Ctrl + F | 
| Mean Filter | M | 
| Median Filter | N | 
| Soft Blur Filter | S | 
| Gaussian Blur Filter | G | 
| Sharpen Image | P | 
| Convert to Grey | V | 
| Contrast and Brightness | C | 
| Posterize | Z |
| Start Recording (Macro) | Shift + R |
| Stop Recording (Macro) | Shift + S |
| Load Macro | Shift + L |
| Draw Line | Alt + L |
| Draw Rectangle | Alt + R |
| Draw Filled Rectangle | Alt + T | 
| Draw Oval | Alt + O |
| Draw Filled Oval | Alt + V |
| Choose Colour | Shift + C |
| Free Draw | D |
| Top Left Emboss | 1 |
| Mid Left Emboss | 2 |
| Top Mid Emboss | 3 |
| Top Right Emboss | 4 |
| Mid Right Emboss | 5 | 
| Bottom Right Emboss | 6 |
| Bottom Middle Emboss | 7 |
| Bottom Left Emboss | 8 | 
| Horizontal Sober | 9 |
| Vertical Sober | 0 |



For all non-filter functions I decided to make the shortcuts double keyed and for the filter functions, I allocated them single key shortcuts. Most of these follow conventional shortcuts from popular photo editing softwares (such as Adobe Photoshop).

Seeing as there are 10 different emboss filters, I assigned each number key to each of the filters. For the drawing features, I decided to make the shortcuts double keyed with Alt/Option being the base key in the keyboard shortcut.
However, in the case of the 'free draw' feature, I decided to make it single keyed to make it quicker to use.
___
***Macros***: Jake
Accessed by: Macro menu and the record and stop/save on the toolbar.

The macro function shows it is recording when the toolbar icon changes to the red version. The macro save and load will default to open in the macros folder in andie as macros should be for general use and having them in one place makes sense. 
Found the different object signature problem with legacy macros/ops files, currently this bug is not handled and macros will have to be remade if the methods involved change there signature.

___

***Toolbar for common operations***: Jake

Accessed by: Toolbar frame on the andie window. 

Toolbar includes: Open, save, export, zoom in, zoom out, rotate clockwise, rotate anticlockwise, flip horizontal, flip vertical, macro start and stop recording.
Testing mainly involved checking the implementation of the methods called by the toolbar worked as intended. One issue I ran into was when implementing the resizeFrame method on the toolbar operations as the toolbar was calling a separate instance of actionsPerformed and so with one implementation that method would only work from the menu. However with the current implementation that has been fixed.

----

***Handling edge cases for the filters***: Jack

The default Java convolution isn't perfect and does not deal with the edges of images very well.

I reimplemented the convolution so that only a couple lines of code would have to be changed. The new convolution operation deals with pixels over the edge by choosing nearest pixel.

----

***Negative results from filters***: Jack

The negative results are dealt with during the convolution. In the constructor of the convolution class, a boolean is set to true indicating that an offset needs to be applied for negative results. The rest is automatically dealt with inside the convolution class.

----

## Our Feature
***Free Draw:*** (Jay & Jake)

For the "Show Us Something" section of the project, we decided to implement a 'free draw' feature which allows the user to draw anything they'd like with the mouse. The user also has the ability to change the colour of the free draw tool.

----


## **Exception handling**

Exceptions were handled for when the user tries to save a file but has not opened anything. Handling was added to the save, save-as and export operations. A message will pop up notifying the user that they must first open a file before saving it.

----

## **General Error Cases:**

(Jay) Problem with previous image edits applying to a newly opened image. Fixed this bug by clearing ops and redoOps stacks whenever a new image is opened via the "FileOpenAction" function.

(Jay) A EmptyStackException would be thrown if user tried to undo when there were no edits to undo. Program now catches the exception and prints out "No action to undo" when the EmptyStackException is thrown.

(Jake) Changed the program so that it wraps the frame around the image opened, limited to a minimum size of 520x450. If the image is larger than the users screen size the window will maximise. 

(Jake) Caught errors if the user tried to use any image actions while there was no image present.

(Jake) Stopped program crash if the user tried to open a non image file. The open explorer will now only show jpg, jpeg, png, JFIF files. If more file types are needed they can easily be added.

(Jake) Found a problem with the saveAs method which would show an open dialog box instead of a save dialog box.

(Jake)I think there may still be some edge problems with export and save to do with file extensions, however I think I have the major problems sorted. If the file does not contain a jpg, jpeg, png, JFIF extension, it will have .jpg appended to the file. This can lead to strange naming, if for example the file was test.txt it would be saved as test.txt.jpg. However the save would still work fine.

(Jack) The colour chooser window would only open once and would not open again after it was closed. This was due to the variables being declared and assigned statically. Changing them to non-static and assigining them inside the constructor fixed the issue. 

(Jake) The default image currently being used when opening the program can be saved over. I have added a bandaid fix to the save operation which will add a 1 to the end of the file name to prevent this, however I think there is a more robust solution. 

(Jake) Fixed a problem with drawing operations for when the image was zoomed

(Jake) Had to change implementation of drawing classes to ensure they saved instances of all the variables as using static values makes and undo/redo/save operation unreliable.

(Jake) Had to split the procedural drawing panel and the draw apply method as having a class extend a JComponent adds problems with incompatiblity of serialization. 

(Jake) There is potential for corrupting ops/macro files when the methods involved are changed. This happens due to the object signature changing and rendering its serials differing between versions.

----


