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

No formal testing framework was used but an image with randomly scatted chromatic aberrations was used to test whether the median filter effectively removed these spots while blurring the image. The results come out as expected however the edges of the image is unaffected by the filter due to how the filter iterates through the image.

The current implementation of the filter is known to be very slow when run with radius values of above 4. A more efficient implementation of the filter is planned for further along the project timeline.

----

***Brightness & Contrast:*** Jake 

Accessed by : Colour menu (brightness and contrast option); Keyboard shortcut 'C'

Tested on many images with different colour palettes and sizes. Did some more in depth pixel testing by using a 3x3 pixel image and checking the calculations on the pixels. From this I found that there was some rounding error due to java int division, which was then fixed. Testing on a greyscale image showed some strange behaviour, the lightest values turning yellow. This was fixed with a lower bound converting anything below zero to zero. However I am unsure why the colour would turn yellow as all the different rgb channels in a greyscale image should be flattened. 

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

***Image Export:*** Jake 

Accessed by: File menu(export option); Toolbar (rectangle with an arrow pointing right); Keyboard shortcut CTRL E.

Testing involved checking what happened with or without the inclusion of file extensions. It will now default to outputting a .jpg file if the user does not add a file extension to their file name.

----

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


For all non-filter functions I decided to make the shortcuts double keyed and for the filter functions, I allocated them single key shortcuts. Most of these follow conventional shortcuts from popular photo editing softwares (such as Adobe Photoshop).
___

***Toolbar for common operations***: Jake

Accessed by: Toolbar frame on the andie window. 

Toolbar includes: Open, save, export, zoom in, zoom out, rotate clockwise, rotate anticlockwise, flip horizontal, flip vertical.
Testing mainly involved checking the implementation of the methods called by the toolbar worked as intended. One issue I ran into was when implementing the resizeFrame method on the toolbar operations as the toolbar was calling a separate instance of actionsPerformed and so with one implementation that method would only work from the menu. However with the current implementation that has been fixed.

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

----