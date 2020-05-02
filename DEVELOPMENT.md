- **4/17/20** Init repo
  - Brief README detailing brief description & TODO
  - Noah and Mat added as contributors
  
- **4/19/20** Begin collecting data
  - Deployed survey to collect data
  - TODO converted to issues
 
- **4/21/20** Determined libraries to utilize
  - [Lets-Plot](https://github.com/JetBrains/lets-plot-kotlin)
  - Possibly use one of these libraries (undetermined): [KNumpy](https://github.com/kotlin/kotlin-numpy/) & [KMath](https://github.com/mipt-npm/kmath)
 
- **4/24/20** Setup KTOR server and implement dependancies
  - Began work on setting up the KTOR server
  - Also added all the dependancies for required libraries
  
- **4/26/20** Add extra library dependancies, parse data
  - Added implementations to use Google API to access Google Sheets
  - Began parsing and filtering the data from the survey
  
- **4/27/20**
  - A lot of front-end development
    - Survey loaded onto server
    - Front page completed
    - Add a Home button, and about page
    
- **4/28/20**
  - Added datalore dependancy
  - Had to refactor the way the data was parsed from sheets because Lets-Plot was being "helpful"
  - Images created from responses

- **4/29/20**
  - Refactored how graphs were presented
    - Present three graphs were row
    - The Lets-Plot Kotlin wrapper did not support editing the title, so had to do some choppy scaling based on title length so it would present properly
    - Load graph iframes onto webpage
- **5/1/20**
  - Refactor graphs (again); rescaled
