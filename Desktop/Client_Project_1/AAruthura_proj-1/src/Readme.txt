need to put media query for all
check contact responsive
nav bar correction
nav bar design change
check overrall responsive for both mobile and desktop


.home-main-nav {
    width: 100%;
    height: 9rem;
    display: grid;
    font-size: 17px;
    grid-template-columns: 10rem 3fr 40rem 1fr 1rem;
    backdrop-filter: blur(10px); 
    position: sticky;
    top: 0;
    left: 0;
    z-index: 1000;
    
  }
  
  .home-img {
    height: 110vh;
    width: 700%;
    display: flex;
    justify-content: center;
    color: transparent; /* Makes the text transparent */
    -webkit-text-stroke: 1px #ffffff; /* Adds a white outline */
    text-stroke: 1px #ffffff; /* Fallback for non-webkit browsers */
    background-color: rgba(0, 0, 0, 0.3);
    box-shadow: inset 0 0 0 1000px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    overflow: hidden;
    opacity: 0.94;
    transition: background 0.3s, border-radius 0.3s, opacity 0.3s;
    background-image: linear-gradient(180deg, #0A0A0A91 100%, #F2295B00 100%);
  }
  
  .home-logo {
    display: grid;
    grid-column: 2/3;
    justify-content: start;
    align-items: center;
  }
  
  .home-logo h1 {
    font-size: 28px;
    font-weight: 600;
    text-transform: uppercase;
    color: rgb(253, 253, 253);
    text-align: left;
  }
  
  .home-logoTxt {
    margin-bottom: 5px;
    margin-left: 10px;
  }
  
  .home-menu-link {
    grid-column: 3/4;
  }
  
  .home-menu-link ul {
    height: 10rem;
    display: flex;
    justify-content: space-around;
    align-items: center;
  }
  
  .home-menu-link ul li {
    font-size: 1.8rem;
  }
  
  .home-menu-link ul li a {
    text-transform: capitalize;
    color: rgba(255, 255, 255, 0.8);
    transition: 0.5s;
    font-weight: 600;
  }
  
  .home-menu-link ul li:hover > a {
    font-weight: bold;
    background-color: #22bad8;
    border-radius: 10px;
    color: rgb(250, 250, 250);
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    animation: fillBackground 0.3s forwards;
  }
  
  @keyframes fillBackground {
    0% {
      background-color: transparent;
      height: 0;
    }
    100% {
      background-color: #073842;
      height: 100%;
    }
  }
  
  @media (max-width: 520px) {
    .home-main-nav {
      height: 6rem;
      grid-template-columns: 1rem 4fr 1fr 1fr 1rem;
    }
  
    .home-logo,
    .home-social-media ul {
      height: 6rem;
    }
  
    .home-logo-image {
      width: 2rem;
      height: auto;
      margin-right: 1rem;
      margin-left: 5rem;
    }
  
    .home-logo h2 {
      font-size: 2rem;
    }
  
    .home-social-media {
      height: 6rem;
      display: flex;
      justify-self: end;
      align-items: center;
    }
  
    .home-social-media .home-hamburger-menu {
      display: block;
      font-size: 2.5rem;
    }
  
    .hero-section h1 {
      font-size: 3.8rem;
    }
  }
  
  @media (max-width: 798px) {
    .home-main-nav {
      height: 6rem;
      grid-template-columns: 1rem 2fr 1fr 1fr 1rem;
    }
  
    .home-logo,
    .home-social-media ul {
      height: 6rem;
    }
  
    .home-social-media {
      height: 6rem;
      display: flex;
      justify-self: end;
      align-items: center;
    }
  
    .home-social-media .home-hamburger-menu {
      display: block;
      font-size: 2.5rem;
    }
  }
  
  @media (max-width: 998px) {
    .home-main-nav {
      height: 7rem;
      grid-template-columns: 2rem 2fr 3fr 2rem 2rem;
    }
  
    .home-menu-link {
      display: none;
    }
  
    .home-logo,
    .home-social-media ul {
      height: 7rem;
    }
  
    .home-mobile-menu-link {
      grid-column: 2/4;
      position: relative;
      z-index: 99;
    }
  
    .home-mobile-menu-link {
      background-color: white;
      height: 20rem;
      display: grid;
      grid-column: 2/5;
      align-items: center;
      padding-left: 3rem;
      transition: all 2s linear;
      transform-origin: top;
      box-shadow: rgba(0, 0, 0, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px;
    }
  
    .home-mobile-menu-link ul {
      height: 20rem;
      display: flex;
      justify-content: space-around;
      flex-direction: column;
      align-items: start;
    }
  
    .home-mobile-menu-link ul li:first-child {
      transition-delay: 0.2s;
    }
  
    .home-social-media {
      grid-row: 1/2;
      grid-column: 3/5;
      justify-items: end;
      align-items: center;
      transition: all 2s linear;
    }
  
    .home-social-media .home-social-media-desktop {
      height: 0;
      display: none;
    }
  
    .home-social-media {
      height: 7rem;
      display: flex;
      justify-self: end;
      align-items: center;
    }
  
    .home-social-media .home-hamburger-menu {
      display: block;
      font-size: 2.5rem;
    }
  
    .home-logo-image {
      width: 3rem;
      height: auto;
      margin-right: 1rem;
      margin-left: 5rem;
    }
  }
  
  @media (max-width: 1080px) {
    .home-main-nav {
      height: 8rem;
      grid-template-columns: 2rem 3fr 3fr 1fr 2rem;
    }
  
    .home-logo,
    .home-menu-link ul,
    .home-social-media ul {
      height: 8rem;
    }
  }
  
  @media screen and (max-width: 768px) {
    .home-img {
      background-position: center;
    }
  }
  
  @media (min-width: 999px) {
    .home-social-media .home-hamburger-menu {
      display: none;
    }
  }
  .home-container {
    width: 204.5vh;
    height: 90vw;
    overflow: hidden; 
}

.app12 {
    margin-top: -13vh;
    width: 215vh;
    height: 106.5vh; 
    display: block;
    overflow: hidden;
    position: relative;
    z-index: 1;
}
.slider1 {
    width: 215vh; 
    height: 100vh;
  }
  
  .slider1 img {
    margin-left:-1vh;
    width: 202vh;
    height: 149vh;
    object-fit: cover; 
    animation: zoomInOut 15s infinite alternate;
    
  }
  
  @keyframes zoomInOut {
    0% {
      transform: scale(1);
    }
    50% {
      transform: scale(1.1);
    }
    100% {
      transform: scale(1.2);
    }
  }
  
  @media (max-width: 768px) {
    .home-container {
      width: 100vw;
      height: auto;
    }
  
    .app12 {
      margin-top: -10rem; 
      width: 100vw;
      height: auto;
    }
  
    .slider1 {
      width: 100vw;
      height: auto;
    }
  
    .slider1 img {
      width: 100vw;
      height: 105vh;
    }
  }
  @media (min-width: 320px) and (max-width: 480px) {
    .home-container {
      width: 100vw;
      height: auto;
    }
  
    .app12 {
      margin-top: -8rem; /* Adjusted margin-top */
      width: 100vw;
      height: auto;
    }
  
    .slider1 {
      width: 100vw;
      height: auto;
    }
  
    .slider1 img {
      width: 100vw;
      height: 100vh; /* Adjusted height for better mobile display */
    }
  }
  
.homecon {
    margin-top: -50px; 
    height: 100vh; 
    width: 100vw; 
    z-index: 100; 
    position: relative; 
}

@media (max-width: 768px) {
    .homecon {
        margin-top: 30px;
        margin-left:-13vh; 
        height: auto; 
        width: 120vw; 
        z-index: 100; 
        position: relative; 
    }
}
@media (min-width: 320px) and (max-width: 480px) {
  .homecon {
    margin-top: 20px; /* Adjusted margin-top */
    margin-left: -10vh; /* Adjusted margin-left */
    height: auto;
    width: 100vw; /* Adjusted width to 100vw for full-width */
    z-index: 100;
    position: relative;
  }
}

.homecom2 {
    height: 100vh; 
    width: 100vw;
    margin-top: -20vh; 
    z-index: 100; 
    display: block;
    position: relative; 
    overflow: auto;
}
.home-container{
    width: 204.5vh;
    height: 643vh;
    overflow: hidden; 
}

.infinity-trans12 {
    background-image: linear-gradient(180deg, #0A0A0A91 100%, #F2295B00 100%), url('./scrollhome.jpg');
    background-attachment: fixed;
    background-size: cover;
    background-position: center;
    padding: 100px 0;
    height: 70vh;
    width: 100vw;
    position: relative;
    backdrop-filter: blur(20px);
    background-color: rgba(0, 0, 0, 0.3);
    box-shadow: inset 0 0 0 1000px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    overflow: hidden;
    opacity: 0.94;
    transition: background 0.3s, border-radius 0.3s, opacity 0.3s;
}

.fixed-grid-container1 {
    font-size: 100px;
    margin-left: 1vh;
    margin-top: 15vh;
    color: transparent; /* Makes the text transparent */
    -webkit-text-stroke: 1px #ffffff; /* Adds a white outline */
    text-stroke: 1px #ffffff; /* Fallback for non-webkit browsers */
}
@media (max-width: 768px) {
    .home-container {
        width: 100vw;
        height: auto;
    }
  
    .homecom2 {
        margin-top:0vh;
        height: auto;
    }
  
    .infinity-trans1 {
        margin-top: -10vh;
        height: 50vh;
    }
  
    .fixed-grid-container1 {
        font-size: 50px;
        margin-left: 1vh;
        margin-top: 5vh;
    }
}
@media (min-width: 320px) and (max-width: 480px) {
  .home-container {
    width: 100vw;
    height: auto;
  }

  .homecom2 {
    margin-top: 0;
    height: auto;
  }

  .infinity-trans1 {
    margin-top: -5vh; /* Adjusted margin-top */
    height: 40vh; /* Adjusted height */
  }

  .fixed-grid-container1 {
    font-size: 40px; /* Adjusted font size */
    margin-left: 0.5vh; /* Adjusted margin-left */
    margin-top: 3vh; /* Adjusted margin-top */
  }
}



@media (max-width: 768px) {
    .title2 {
        font-size: 50px;
        margin-right: 0;
        margin-top: 0;
    }
}
@media (min-width: 320px) and (max-width: 480px) {
  .title2 {
    font-size: 40px; /* Adjusted font size */
    margin-right: 0;
    margin-top: 0;
  }
}

.homecom3 {
    margin-top: 20vh;
}

.home-slider {
    margin-right: -90vh;
    margin-top: -50vh;
}

.about-content1 {
    display: flex;
    flex-direction: column;
    align-items: center;
}

@media (max-width: 768px) {
    .about-content1 .text {
        font-size: 20px;
        margin-right: 10;
        margin-top: 80px;
    }
}
@media (min-width: 320px) and (max-width: 480px) {
  .about-content1 .text {
    font-size: 18px; /* Adjusted font size */
    margin-right: 5px; /* Adjusted margin-right */
    margin-top: 60px; /* Adjusted margin-top */
  }
}

.title2 {
    font-size: 50px;
    border-bottom: 0.1px solid rgb(236, 236, 236);
    border-radius: 10px;
    color: #073842;
    margin-right: 110vh;
    margin-top: -20vh;
}

@media (max-width: 768px) {
    .title2 {
        font-size: 50px;
        margin-right: 0;
        margin-top: 0;
    }
}
@media (min-width: 320px) and (max-width: 480px) {
  .title2 {
    font-size: 30px; /* Adjusted font size */
    margin-right: 0;
    margin-top: 0;
  }
}

  .homecom3{
    margin-top: 20vh;
  }


  .home-slider{
    margin-right: -90vh;
    margin-top: -50vh;
  }
  .swiper {
    width: 400px;
    height: 480px;
  }
  
  .swiper-slide {
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 18px;
    font-size: 22px;
    font-weight: bold;
    color: #fff;
  }
  
  .swiper-slide:nth-child(1n) {
    background-image: url('../Carousle_content/img2.jpg');
    background-size: 100% 100%;
  }
  
  .swiper-slide:nth-child(2n) {
    background-image: url('../Carousle_content/img3.jpg');
  background-size: 100% 100%;
  }
  
  .swiper-slide:nth-child(3n) {
     background-image: url('../Carousle_content/img4.jpg');
     background-size: 100% 100%;
  }
  
  .swiper-slide:nth-child(4n) {
    background-image: url('../Carousle_content/img5.jpg');
    background-size: 100% 100%;
  }
  
  .swiper-slide:nth-child(5n) {
    background-image: url('../Carousle_content/img6.jpg');
    background-size: 100% 50%;
  }
  
  .swiper-slide:nth-child(6n) {
   background-image: url('../Carousle_content/img11.jpg');
   background-size: 100% 100%;
  }
  
  .hometagline{
    font-style: bold;
    font-size: 30px;
    color: #073842;
    margin-left: -80vh;
    margin-top: 10vh;
  }
  .homecontent{
    font-style: normal;
    font-size: 20px;
    color: #073842;
    margin-left: 11vh;
    margin-top: 6vh;
    text-align: justify;
    
  }
  .minibox{
   
    font-style: normal;
    font-size: 25px;
    text-align: justify;
    color: #073842;
    height: 50vh;
    width: 100vh;
    margin-left: 11vh;
  }
  @media (max-width: 768px) {
    .home-slider {
      display: block; 
      margin-right: 0; 
      margin-top: -150px;
  
    }
  
    .swiper {
      width: 40vh; 
      height: 60vh; 
      background-color: #f2f2f2; 
      border: 1px solid #ccc; 
     
    }
  
    .swiper-slide {
      font-size: 14px; 
      padding: 50px; 
    }  
    .hometagline {
        margin-left: 0;
        margin-top: 5vh;
    }

    .homecontent {
        margin-left: 0;
        margin-top: 3vh;
    }

    .minibox {
        font-size: 13px;
        margin-left: 0px;
        margin-top: 80px;
        justify-content: left;
    }
}
@media (min-width: 320px) and (max-width: 480px) {
  .home-slider {
    display: block;
    margin-right: 0;
    margin-top: -150px;
  }

  .swiper {
    width: 100%; /* Adjusted width for full-width */
    height: 30vh; /* Adjusted height */
    background-color: #f2f2f2;
    border: 1px solid #ccc;
  }

  .swiper-slide {
    font-size: 12px; /* Adjusted font size */
    padding: 20px; /* Adjusted padding */
  }

  .hometagline {
    margin-left: 0;
    margin-top: 3vh; /* Adjusted margin */
  }

  .homecontent {
    margin-left: 0;
    margin-top: 2vh; /* Adjusted margin */
  }

  .minibox {
    font-size: 12px; /* Adjusted font size */
    margin-left: 0;
    margin-top: 30px; /* Adjusted margin */
    justify-content: left;
  }
}


.title12{
    font-size: 50px;
    border-bottom: 0.1px solid rgb(236, 236, 236);
    border-radius: 10px;
    color: #073842;
    margin-right: 120vh;
    margin-top: 12vh;
}

.tagline12{
    font-size: 20px;
    color: #073842;
    margin-left: -70vh;
    margin-top: -2vh;
}
@media (max-width: 768px) {
  .title12 {
    font-size: 30px;
    margin-right: 0;
    margin-top: 1vh;
  }

  .tagline12 {
    font-size: 15px;
    margin-left: 0;
    margin-top: 1vh;
  }
}

@media (min-width: 320px) and (max-width: 480px) {
  .title12 {
    font-size: 20px;
    margin-right: 0;
    margin-top: 1vh;
  }

  .tagline12 {
    font-size: 12px;
    margin-left: 0;
    margin-top: 1vh;
  }
}
