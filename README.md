# android-compose-hello-world
Android Compose hello world project

## Issue
To better explain the issue reported at:
https://github.com/isaac-udy/Enro/issues/52

The issue: the ViewModels of bottom tab never cleared, even when the host MainActivity onDestroy,
which is done by clicking back button until exit the app.

Actually the onCleared() method will never invoked.

### This sample's issue
To give a better/concrete example of why this is an issue, this sample has
* a singleton `SharedStateManager` to send out events.
* a DashboardViewModel collects the event from its init block.

After user click back to exit the app, MainActivity onDestroy, but the viewModel not cleared, so the event is keep being collected.

When the user launch the app again (from launcher or recent app), a new MainActivity created,
When navigate to dashboard tab, a new DashboardViewModel is also initialized and start to collect events from shared flow.

So every singleton event will be collected twice.
See the log as:
```
 DashboardViewModel init 180068328
 viewmodel 180068328 start to collect
 viewmodel 12774153 collect from manager: Event(name=event: 11)
 viewmodel 180068328 collect from manager: Event(name=event: 11)
```

If we keep doing so, the log for same event would be replicated 3, 4 times or more.

This sample just print logs to make things simple, if we want to show a toast or snackbar for the event,
it will show multiple times.


## Expected
We suppose when MainActivity onDestroy, the tabs' viewModel will all be cleared.
If the viewModelScope get cancelled from onCleared, the events won't be collected multiple times.

Maybe I'm not using Enro in right way, or not combining it with Hilt correctly.

The expected behavior would be somehow make the viewmodel `init` and `clear` as a couple,
So users won't carelessly init multiple times and not cleared once.
