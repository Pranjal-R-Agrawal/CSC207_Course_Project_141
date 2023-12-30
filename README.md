# Start-Up Generator
___

A Java-powered idea generator and collaboration forum for innovative startups. 


* **Startup/Business Model Generation:** Our program generates a random startup idea and a possible business model for the generated idea using language models.

* **Posting Startup Ideas to Forum:** Users can post these ideas to a forum that allows others to read the post, and comment if they are interested in working on the project.

* **Finding Projects to Work On:** Users can search for posts so that they can find posts they would like to work on and comment on them.

* **Adding Collaborators:** The author of a post can add interested commenters as collaborators they want to work with.

### Purpose: 
___
To keep ideas alive by overcoming the challenge of finding and assembling skilled teams to champion your visionary pursuits.


### Project Design: 
___

* **Clean Architecture:** Our primary focus of the project was to successfully implement layers of the Clean Architecture to facilitate code organization, extensibility and testing. 

* **SOLID Principles and Design Patterns:** 
   * Used SOLID principles like **Interface Segregation, Dependency Inversion and Single Responsibility** to support the Clean Architecture better. 
   * Optimized code quality using Design Patterns like **Builder, Factory and Dependency Injection** to solve common code implementation problems. 

* **API:** 
   * POST Requests to the [Hugging Face's Inference API](https://huggingface.co/inference-api) 
   * 2 AI Text Generation endpoints ([mistralai/Mistral-7B-Instruct-v0.1](https://huggingface.co/mistralai/Mistral-7B-Instruct-v0.1) and [Salesforce/codegen-350M-mono](https://huggingface.co/Salesforce/codegen-350M-mono)) used to generate a business model given an idea prompt from the database. 
   * Open to extension of new AI models 

* **Testing:** 
   * **Unit tests** for the Entities, Interactors and Database. 
   * **Integration tests** for the Interactors with the real database and API. 
   * **End-to-end tests** to test each Use Case. 
   * Achieved 92% line coverage for the Interactor tests (Application Logic) 

* **User Interface:** Java Swing and AWT components  

### How to Run the Project: 
___
* Download the ideas.csv from https://www.kaggle.com/datasets/bilalelebi/business-ideas-generated-with-gpt3/data. Extract the zip file and place the idea.csv in the src/main/java/data_access folder of the repository. 

* Generate a User Access Token on Hugging Face. After creating an account, go to Profile > Settings > Access Tokens. Click on the New Token Button with any desired name and a Write Role. Copy the token. Create a File type (this is not a .txt file) with the name **API_TOKEN** under the src/main/java/api folder of the repository and edit the first line of the file with **Bearer [paste your token here]** and save the file. Learn to create a File type from https://www.thewindowsclub.com/create-a-file-without-extension-in-windows

* To obtain the database connection string, start by creating or logging into your MongoDB Atlas account. Once you’re logged in, deploy a new cluster. After the cluster is deployed, navigate to Security > Network Access > Add IP Address, click on the ‘Allow Access from Anywhere’ button, and confirm your changes by clicking the ‘Confirm’ button. Next, go to Overview > Connect > Drivers and copy the connection string. Finally, in the directory ‘src/main/java/data_access’, create a .txt file titled ‘database_connection’ and paste the previously copied connection string into this file.

* Now you are ready to run the Main file of the project!

### Credits: 
___
Developed and maintained by: 

* [Sidharth Sawhney](https://github.com/SidharthSawhney)
* [Pranjal Agrawal](https://github.com/Pranjal-R-Agrawal)
* [Anbuselvan Ragunathan](https://github.com/anbu1504)
* [Yathusan Koneswararajah](https://github.com/YathusanK)
* [Tanmay Shinde](https://github.com/Tanmay-Shinde)                                                            

This project was created as a part of the Software Design course project at the University of Toronto.
