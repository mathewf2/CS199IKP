# CS199IKP
## COVID - 19 Visualization
-------------------------
This project was created by [Noah Rogers](https://github.com/nnrogers515) and [Mat Farley](https://github.com/mathewf2) for University of Illinois' CS 199: IKP (Imperative Kotlin Programming).

The purpose of this project is visualize a collection of data regarding the impacts of COVID-19. We decided to head in in a data science direction as it was an interest for both of us, and because of Kotlin's rise in libraries and compatibilty within the field.

Ultimately, our goal was to collect data ourselves (via Google Forms, accessed via Google Sheets API), represent that data through [Lets-Plot-Kotlin](https://github.com/JetBrains/lets-plot-kotlin), host it on a [KTOR](https://github.com/ktorio/ktor) server, utilize coroutines to implement a live updating feature to constantly fetch new survey results, and host it on AWS.

Noah primarily dealth with the front-end work and the coroutines, while Mat worked on the backend and data collection. Ultimately, our biggest struggles arised from working with some of incompatibility, or lack of documentation, for Kotlin considering it's a new language. Lets Plot, though written in Kotlin natively, is primarily a Python library where not all of the features have been appropriately ported over to support the Kotlin language. Lets Plot does, however, allow for a huge variety of exciting visual representations to play with, which can lead to some powerful graphs (more powerful than ours, surely!)

The survey and purpose, though many steps were taken to avoid this, are inherently biased towards individuals in a college setting. We spent a lot of time trying to make it as generic and relevant to all populations as much as possible, but we still faulted there in some regard. Both of us are a bit new to the field of data science, so it certainly is not our expertise.

This README will be updated with appropriate instructions to access the site, hosted by AWS, where the survey and data will be hosted once it is uploaded.

In the mean time, you can access our site locally after cloning. The default port is 8080.

Upon first time running, you will need to accept permissions through a Google account. Afterwards, this information will be saved in tokens/StoredCredential.


### TODO:

- [X] Create and send out suvey

- [X] Review Kotlin data science libraries

- [X] Get a shell of a KTOR server running

- [X] Process survey results into data visualization

- [X] Implement some sort of "live" updating feature (if applicable)

- [ ] Make the graphs more meaningful & aesthetically pleasing
  - We have *a lot* of data that is currently not being used, as well.

- [ ] Host on AWS

- [ ] Some documentation wouldn't hurt
