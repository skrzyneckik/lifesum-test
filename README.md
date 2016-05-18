##On disabling ProGuard
As mentioned during my interview with Phillipe, I'm fairly sure that ProGuard would require some time to properly set up along code generation tools, and time is not something I had a lot of, so unfortunately I cannot allocate any for researching on this.

##On disabling release variants
Although very unlikely, it could happen that accidentally a release APK makes it to Google Play, which is not intended to happen. By disabling release variants on the corresponding module, we prevent this situation completely.

##On architecture

### On the choice of DBFlow, RxJava and Dagger
First of all, I already have a bit of experience with all of these, and with a tight time restriction and candidate solutions that seem to be good enough, I didn't think twice about experimenting with other frameworks. This said, these tools are all I should ever be needing to make a robust and maintainable implementation of [Clean Architecture](https://github.com/googlesamples/android-architecture/tree/todo-mvp-clean). I know that the people responsible for this sample have developed their own Clean Architecture framework, but it does not support RxJava, which was important to me because of the way I deal with data extracted from the database, plus it is meant to be used with Dagger 1, whereas my little experience with DI is with Dagger 2.

### Why no MVP, without the domain layer that Clean Architecture adds?
Because that would mean adding some clutter in the app module, and taking into account that the effort required to set up this additional layer is not particularly large, the trade-off is worth.

### Why no MVVM with databinding?
I am not sold on this just yet. Removing boilerplate from Java is always welcome, but doing so at the cost of adding XML does not look like a great idea to me. I expect this to change in the near future now that the library seems to be adopting support for bi-directional bindings, (although even if that was the case already the decision would not change because the app is not meant to support update operations), but for now, from my point of view, ButterKnife seems like an option to address the same issue, in a different way yes, but with less of a con.

### And Redux (+ maybe React)?
First, I barely know any JS, so this would have to be more like Jedux/Anvil. And anyway, although my knowledge of this architecture is enough to see that it could be a good fit due to the simple and almost completely unidirectional flow of data, it doesn't go much beyond that, which means that I do not consider myself ready to deliver a good implementation of it just yet.