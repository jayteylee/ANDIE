## Context
Andie is an image editing program. 'Andie' stands for A-non-destructible-image-editing program. This means that all changes made to an image (greyscale, blurring, etc.) can be undone. The edited image is output with an extra file that stores the operations applied on the image. 

## Github repo
The repository can be cloned from https://altitude.otago.ac.nz/cosc202-vscoders/andie.git

## Compiling and running
The main class for this project is Andie.java inside of the cosc202.andie package.

---


## Filters

***Median Filter:*** Jay

Accessed by: Filter menu (Median filter option) and keyboard shortcut 'N'

Tested on many images with different colour palettes and sizes.

No formal testing framework was used but an image with randomly scatted chromatic aberrations was used to test whether the median filter effectively removed these spots while blurring the image. The results come out as expected however the edges of the image is unaffected by the filter due to how the filter iterates through the image.

The current implementation of the filter is known to be very slow when run with radius values of above 4. A more efficient implementation of the filter is planned for further along the project timeline.

----

***Rotate Image Clockwise:*** Jay

Accessed by: Image menu and keyboard shortcut Ctrl + ]

No formal testing framework was used but a variety of images with different colour palettes and sizes were used to test the implementation of the function.

***Rotate Image Anti-Clockwise:*** Jay

Accessed by: Image menu and keyboard shortcut Ctrl + [

No formal testing framework was used but a variety of images with different colour palettes and sizes were used to test the implementation of the function.


---
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

___

## **General Error Cases:**

(Jay) Problem with previous image edits applying to a newly opened image. Fixed this bug by clearing ops and redoOps stacks whenever a new image is opened via the "FileOpenAction" function.

(Jay) A EmptyStackException would be thrown if user tried to undo when there were no edits to undo. Program now catches the exception and prints out "No action to undo" when the EmptyStackException is thrown.
