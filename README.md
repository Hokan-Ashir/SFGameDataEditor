# SFGameDataEditor
SpellForce game GameData.cff file editor, for modification and fixes creation

# What is it
Java GUI editor of original SpellForce game (see http://spellforce.com/) Gamedata.cff files. 

Allowing you to change almost any existing in-game ability parameter like mana usage, range, skill requirement,
ability effect, spell type and so on.

IMPORTANT: 

See "What it can't do" section below to be awared of restrictions.

# What inside
 - Java 7 - for diamonds and multiple catch exceptions
 - Swing - for GUI, obviously
 - XDeltaEncoder - for making binary differences between files (see https://github.com/mantlik/xdeltaencoder)
 - SHA-512 - for differencing original/modification files
 - Winzipaes - for encrypted zip-files creation (see https://code.google.com/p/winzipaes/ or MAVEN central repository)
 - Quite messy and excessively heavy architecture - as result too many class instances and too much memory eaten

# What it can do
 - Editing skill requirements, i.e how many strength you need to level-up your Heavy Combat Art skill to level 7
 - Editing spell parameters, i.e. how far you can cast your "Fireball" or how many hops can make "Chain Pain"
 - Creating compact sf-mod files, which stores your modifications
 - Loading sf-mod files created by other users
 - Merging sf-mod files with your GameData.cff files to get modificated data, which you can test in game

# What it can't do
 - Set ability mana usage field to 0
 
 Because of specific spell data seaching mechanisms, to determine where spell data begins application uses "non-zero mana usage" assumption
 - Almost no validations on entering values
 
 Text fields has only "enter only digit", "enter value greater than zero" validation. So if you will enter, i.e. 65536 in 2-bytes field (which has maximum value of 65535), you may expect application will crush and stun forever
 - No other languages except English and Russian, yet ability to extend with French and German languages
 
 You can easily change localizations by changing xml-configuration files or write new ones, like French or German
 
 NOTE: 
 
 File name format MUST look like "configuration_XX.xml", where XX is language signature, FR for French and DE for German. Register irrelevant

# How to launch it
 - Install, at least, Java 7 from Oracle site (see https://www.java.com/download/)
 - Copy SFGameDataEditor.jar, configuration_EN.xml AND/OR configuration_RU.xml, depending which language you would like
 - Double-click on SFGameDataEditor.jar OR run with "java -jar SFGameDataEditor.jar" comand in console
 
# How to use it
 - After launch you can select editor's language, which is loading via configuration files
 - Then you must select, at least, original file (or already modificated file, see NOTE below); and sf-mod file optionaly
 - After changing different parameters you can create sfmod-file, which will consists of changes you made via "Create sf-mod file" button
 - You can also load sf-mod-file during editing with "Load sf-mod file" button
 
 WARNING:
 
 This will erase all your current changed
 - Also after closing application you will get "GameData.cff.mod" file in the same directory, where original GameData.cff, which includes all your changes
 
 NOTE:
 
 Because of compatibilty, editor stores hash of original file based on which sf-mod-file created. 
 This is made to exclude file format errors. 
 So if you will try to load sf-mod file, which was created not based on original file, you selected, you will have corresponding message.
 
 
# SfMod-files format
  OriginalFile = file, you've chosen as original in file selection dialogue
  
  ModificationFile = file which storing all your current changes
 - SfMod-file creation procedure
    - TmpFile = XDeltaEncoder(OriginalFile, ModificationFile, DIFF)
    - HASH = SHA-512(OriginalFile)
    - SfMod-file = winzipaes(TmpFile, HASH)
 - SfMod-file loading procedure
    - HASH = SHA-512(OriginalFile)
    - TmpFile = winzipaes(SfMod-file, HASH)
    - ModificationFile = XDeltaEncoder(OriginalFile, TmpFile, MERGE)

# What YOU can do
 - Create modificated GameData.cff files for your own need, or distribute them with people, who can't access editor for some resons
 - Create sf-mod-files in terms of "mods" to original game, which people can use with ease, cause sf-mod-files have much lesser size
 - Create collections of sf-mod-files with this action chain: 
    1. Load original file, i.e. GameData.cff
    2. Load sf-mod-file_1 - on this stage you will have GameData.cff.mod with sf-mod-file_1 applied to it
    3. Exit
    4. Rename GameData.cff.mod to GameData.cff.mod1 i.e., for exclude name clashing
    5. Load GameData.cff.mod1
    6. Load sf-mod-file_2 - on this stage you will have GameData.cff.mod with sf-mod-file_1 AND sf-mod-file_2 applied to it
    7. ...

# How YOU can help
 - Report new issues about bugs, typos, code advises etc. via https://github.com/Hokan-Ashir/SFGameDataEditor/issues/new
 - Report of spell parameters which you aware of, those which marked as "???"
 - Write French and German localization files, look a like existing "configuration_EN.xml" one
 - Download source code, change, refactor it, write me to commit changes
 - Fork this hub and make own version of editor

# TODO
 - Complete architecture redesign to minimize number of class instances, increase extensibility, make cleaner and module code
 - Add more flexible ability to merge multiple sf-mod files
 - Extend sf-mod-files format to include description
 - Extend with item, merchants, buildings, units views
 - Extend with French and German i18n
 - Replace i18n via XML with resouseBundle files 
 - Add i18n in JOptionPane windows
 - Replace message-windows processing with notifications and separate threads usage

# Special thanks
 - This application won't be possible without information grabbed from this forum - http://spellforcefanforum.hostoi.com/index.php
 - Thanks to rupuzioks and VolterPL for parsing GameData.cff format and gathering info from official english and german sites
 - You can also support VolterPL with his own .NET editor, which you can grab here http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=242&sid=13dc65505d9b57498789cd9574831658, hope author won't be against

# Contacts
 - Report new issue here via https://github.com/Hokan-Ashir/SFGameDataEditor/issues/new
 - achkasov.anton.92@gmail.com
