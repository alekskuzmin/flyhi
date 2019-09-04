# flyhi
An MVI architecture tryout

This is my take on the trendy MVI. User intents captured from View (fragments) are dispatched through Presentation (view model) via Dispatcher. Dispatcher emits corresponding Actions which results modify the app state. Since the state in StateHolder is immutable the Reducer takes care of producing the new state by applying changes to the previous one. Presentation then renders the modified state to be displayed in the View (via data binding).

Possible improvements include:
- modularization to hide the dependencies implementations behind the interfaces (which would be injected instead of the actual classes);
- augmentation of the Repository with the Cache layer;
- augmentation of the state control with state history.
