from tkinter import *
import time

def update_time():
    current_time = time.strftime("%I:%M:%S %p")
    lbl.config(text=current_time)
    
    # Update every 1000 milliseconds (1 second)
    clock.after(1000, update_time)  

clock = Tk()
clock.title("Digital Clock")
clock.geometry("636x160")

lbl = Label(clock, font=("Times new roman", 90))
lbl.pack()

# Initial call to start the clock updating
update_time()  

clock.mainloop()
